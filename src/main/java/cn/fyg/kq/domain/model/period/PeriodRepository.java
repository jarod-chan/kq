package cn.fyg.kq.domain.model.period;

import java.util.List;

import org.springframework.data.repository.Repository;

import cn.fyg.kq.domain.shared.kq.Comp;


public interface PeriodRepository extends Repository<Period, Long> {
	
	Period save(Period kaoqin);
	
	List<Period> findAll();
	
	void delete(Long id);
	
	Period findOne(Long id);

	List<Period> findByMonthitem_YearAndMonthitem_MonthAndComp(int year,
			int month, Comp comp);

}
