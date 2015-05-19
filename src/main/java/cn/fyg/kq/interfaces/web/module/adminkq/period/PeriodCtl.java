package cn.fyg.kq.interfaces.web.module.adminkq.period;

import static cn.fyg.kq.interfaces.web.shared.message.Message.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CalculateFacade;
import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodState;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;


@Controller
@RequestMapping("period")
public class PeriodCtl {
	
	private static final String PATH = "period/";
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
	
	
	@Autowired
	KaoqinService kaoqinService;
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("periodId") Long periodId){
		Period peroid = this.periodService.find(periodId);
		
		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(peroid);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		Sort sort=null;
		
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		for (Kaoqin kaoqin : kaoqinList) {
			this.kaoqinService.delete(kaoqin.getId());
		}
		this.periodService.delete(periodId);
		
		return "redirect:list";
	}

}
