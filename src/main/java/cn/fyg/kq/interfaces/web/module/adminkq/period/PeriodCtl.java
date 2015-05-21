package cn.fyg.kq.interfaces.web.module.adminkq.period;

import static cn.fyg.kq.interfaces.web.shared.message.Message.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CalculateFacade;
import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodState;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;


@Controller
@RequestMapping("period")
public class PeriodCtl {
	
	private static final String PATH = "period/";
	private interface Page {
		String LIST = PATH + "list";
		String CALRESULT = PATH + "calresult";
	}
	
	@Autowired
	PeriodService periodService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Period> periodList = this.periodService.findAll();
		map.put("periodList", periodList);
		return Page.LIST;
	}
	
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create(Period period,Map<String,Object> map,RedirectAttributes redirectAttributes){
		String comp="fangchan";
		boolean exist = this.periodService.exist(period.getMonthitem(),comp);
		if(exist){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("当前月份期间已经存在！"));
			return "redirect:list";
		}
		period.setState(PeriodState.create);
		period.setComp(comp);
		this.periodService.save(period);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}
	
	@Autowired
	CalculateFacade calculateFacade;
	
	@RequestMapping(value="docal",method=RequestMethod.POST)
	public String docal(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Period period = this.periodService.find(periodId);
		period.setState(PeriodState.docal);
		this.periodService.save(period);
		this.calculateFacade.calculate(period);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("计算执行中！"));
		return "redirect:list";
	}
	
	@RequestMapping(value="{periodId}/calresult",method=RequestMethod.GET)
	public String toCalresult(@PathVariable("periodId") Long periodId,Map<String,Object> map){
Period period = this.periodService.find(periodId);
		
		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		Sort sort=null;
		
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		map.put("kaoqinList", kaoqinList);
		return Page.CALRESULT;
	}
	
	
	@RequestMapping(value="produce",method=RequestMethod.POST)
	public String produce(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Period period = this.periodService.find(periodId);
		
		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		Sort sort=null;
		
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		for (Kaoqin kaoqin : kaoqinList) {
			kaoqin.setState(KaoqinState.produce);
			this.kaoqinService.save(kaoqin);
		}
		period.setState(PeriodState.produce);
		this.periodService.save(period);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("考勤单生产完成！"));
		return "redirect:list";
	}
	
	
	@Autowired
	KaoqinService kaoqinService;
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("periodId") Long periodId,RedirectAttributes redirectAttributes){
		Period period = this.periodService.find(periodId);
		
		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		Sort sort=null;
		
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		for (Kaoqin kaoqin : kaoqinList) {
			this.kaoqinService.delete(kaoqin.getId());
		}
		this.periodService.delete(periodId);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("删除完成！"));
		return "redirect:list";
	}

}
