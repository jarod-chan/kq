package cn.fyg.kq.interfaces.web.kq.period;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.kq.period.Period;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;


@Controller
@RequestMapping("period")
public class PeriodCtl {
	
	private static final String PATH = "kq/period/";
	private interface Page {
		String LIST = PATH + "list";
	}
	
	@Autowired
	PeriodService periodService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Period> periodList = this.periodService.findAll();
		map.put("periodList", periodList);
		return Page.LIST;
	}
	
	@RequestMapping(value="calculate",method=RequestMethod.POST)
	public String calculate(Period period,Map<String,Object> map,RedirectAttributes redirectAttributes){
		period.setComp("fangchan");
		this.periodService.save(period);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("period") Long period){
		this.periodService.delete(period);
		return "redirect:list";
	}

}