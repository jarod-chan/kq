package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.period.Period;

public interface PeriodService {
	
	Period save(Period period);
	
	List<Period> findAll();
	
	void delete(Long id);
	
	Period find(Long id);

}
