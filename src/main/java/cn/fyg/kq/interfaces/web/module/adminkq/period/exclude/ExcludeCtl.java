package cn.fyg.kq.interfaces.web.module.adminkq.period.exclude;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ExcludeService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.exclude.Exclude;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.mvc.CustomEditorFactory;


@Controller
@RequestMapping("period/{periodId}")
public class ExcludeCtl {
	
	private static final String PATH = "exclude/";
	private interface Page {
		String EDIT = PATH + "edit";
	}
	
	@Autowired
	ExcludeService excludeService;
	@Autowired
	PeriodService periodService;
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, CustomEditorFactory.getCustomDateEditor());
	}
	
	@RequestMapping(value="exclude",method=RequestMethod.GET)
	public String toEdit(@PathVariable("periodId")Long periodId,Map<String,Object> map,RedirectAttributes redirectAttributes){
		Period period = this.periodService.find(periodId);
		List<Exclude> excludeList=this.excludeService.periodExclude(period);
		
		ExcludePage excludePage = new ExcludePage();
		excludePage.init(period, excludeList);
		map.put("page", excludePage);
		map.put("period", period);
		
		return Page.EDIT;
	}
	
	@RequestMapping(value="exclude/save",method=RequestMethod.POST)
	public String save(@PathVariable("periodId")Long periodId,ExcludePage excludePage,Map<String,Object> map,RedirectAttributes redirectAttributes){

		for(ExcludeBean bean:excludePage.getExecludeBeans()){
			Exclude exclude = bean.getExclude();
			if(bean.isChecked()&&exclude.getId()==null){
				exclude.setPeriod_id(periodId);
				this.excludeService.save(exclude);
			}else if(!bean.isChecked()&&exclude.getId()!=null){
				this.excludeService.delete(exclude.getId());
			}
		}
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return String.format("redirect:/period/%s/exclude",periodId);
	}
	
	

}
