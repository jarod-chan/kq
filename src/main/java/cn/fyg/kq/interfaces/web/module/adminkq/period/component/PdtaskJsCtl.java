package cn.fyg.kq.interfaces.web.module.adminkq.period.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.application.PdtaskService;
import cn.fyg.kq.domain.model.pdtask.Pdtask;
import cn.fyg.kq.domain.model.pdtask.TaskState;

@Controller
@RequestMapping("period")
public class PdtaskJsCtl {
	
	
	@Autowired
	PdtaskService pdtaskService;
	
	@RequestMapping(value="pdtask.json",method=RequestMethod.GET)
	@ResponseBody 
	public Pdtask period(@RequestParam("periodId") Long periodId){
		boolean havePeriodTask = this.pdtaskService.havePeriodTask(periodId);
		if(havePeriodTask){
			return this.pdtaskService.get(periodId);
		}
		Pdtask pdtask = new Pdtask();
		pdtask.setState(TaskState.end);
		return pdtask;
	}

}
