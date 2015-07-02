package cn.fyg.kq.interfaces.web.module.adminkq.period.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.fyg.kq.domain.model.user.User;

@Component
public class PeriodFacade {
	
	@Autowired
	DoCalculate doCalculate;
	@Autowired
	DoProduce doProduce;
	@Autowired
	DoDelete doDelete;
	
	@Async
	public void calculate(Long periodId){
		doCalculate.calculate(periodId);
	}
	
	@Async
	public void produce(Long periodId,User user){
		this.doProduce.produce(periodId, user);
	}
	
	@Async
	public void delete(Long periodId){
		this.doDelete.delete(periodId);
	}

}
