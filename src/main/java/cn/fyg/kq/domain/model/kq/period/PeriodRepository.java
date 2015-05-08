package cn.fyg.kq.domain.model.kq.period;

import java.util.List;

import org.springframework.data.repository.Repository;


public interface PeriodRepository extends Repository<Period, Long> {
	
	Period save(Period kaoqin);
	
	List<Period> findAll();
	
	void delete(Long id);
	
	Period findOne(Long id);

}
