package cn.fyg.kq.interfaces.web.module.adminkq.period.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.PdtaskService;
import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.application.facade.KaoqinFacade;
import cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodState;
import cn.fyg.kq.domain.model.user.User;

@Component
public class DoProduce {
	
	@Autowired
	PdtaskService pdtaskService;
	@Autowired
	PeriodService periodService;
	@Autowired
	KaoqinFacade kaoqinFacade;
	@Autowired
	KaoqinService kaoqinService;

	public void produce(Long periodId,User user){
		this.pdtaskService.startPeriodTask(periodId);
		
		Period period = this.periodService.find(periodId);

		Specification<Kaoqin> inPeriod = KaoqinSpecs.inPeriod(period);
		Specifications<Kaoqin> specs=Specifications.where(inPeriod);
		Sort sort=new Sort(Direction.ASC,"id");
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll(specs, sort);
		
		for (Kaoqin kaoqin : kaoqinList) {
			this.kaoqinFacade.startProcess(kaoqin, user);
		}
		period.setState(PeriodState.produce);
		this.periodService.save(period);
		
		this.pdtaskService.endPeriodTask(periodId);
	}

}
