package cn.fyg.kq.interfaces.web.module.adminkq.period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.period.Period;

@Controller
@RequestMapping("period")
public class PeriodJsCtl {
	
	
	@Autowired
	PeriodService periodService;
	
	@RequestMapping(value="period.json",method=RequestMethod.GET)
	@ResponseBody 
	public Period period(@RequestParam("periodId") Long periodId){
		Period period = this.periodService.find(periodId);
		return period;
	}

}
