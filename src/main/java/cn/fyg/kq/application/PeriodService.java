package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.shared.kq.Comp;

public interface PeriodService extends ServiceQuery<Period> {
	
	Period create(MonthItem monthItem,Comp comp);
	
	Period save(Period period);
	
	List<Period> findAll();
	
	void delete(Long id);
	
	Period find(Long id);
	
	boolean exist(MonthItem monthItem,Comp comp);

}
