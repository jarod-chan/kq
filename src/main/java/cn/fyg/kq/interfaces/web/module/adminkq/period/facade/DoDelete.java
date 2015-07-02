package cn.fyg.kq.interfaces.web.module.adminkq.period.facade;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import cn.fyg.kq.application.ExcludeService;
import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.PdtaskService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.exclude.Exclude;
import cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.period.Period;

@Component
public class DoDelete {
	
	@Autowired
	PeriodService periodService;
	@Autowired
	KaoqinService kaoqinService;
	@Autowired
	ExcludeService excludeService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	PdtaskService pdtaskService;
	
	
	public void delete(Long periodId){
		this.pdtaskService.startPeriodTask(periodId);
		
		Period period = this.periodService.find(periodId);
		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		
		List<Exclude> periodExclude = this.excludeService.periodExclude(period);
		for(Exclude exclude:periodExclude){
			this.excludeService.delete(exclude.getId());
		}
		
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs);
		for (Kaoqin kaoqin : kaoqinList) {
			String processId = kaoqin.getProcessId();
			if(processId!=null){
				this.runtimeService.deleteProcessInstance(processId, "delete");
			}
			this.kaoqinService.delete(kaoqin.getId());
		}
		
		this.periodService.delete(periodId);
		this.pdtaskService.endPeriodTask(periodId);
	}

}
