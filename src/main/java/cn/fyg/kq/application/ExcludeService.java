package cn.fyg.kq.application;

import java.util.Date;
import java.util.List;

import cn.fyg.kq.domain.model.exclude.Exclude;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.shared.kq.Ampm;

public interface ExcludeService {
	
	  Exclude create(Long period_id,Date date,Ampm ampm);

	List<Exclude> periodExclude(Period period);

	Exclude save(Exclude exclude);

	void delete(Long id);

}
