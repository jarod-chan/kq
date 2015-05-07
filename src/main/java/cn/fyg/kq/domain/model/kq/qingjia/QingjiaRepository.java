package cn.fyg.kq.domain.model.kq.qingjia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface QingjiaRepository extends Repository<Qingjia, Long>, JpaSpecificationExecutor<Qingjia> {
	
	Qingjia save(Qingjia qingjia);
	
	List<Qingjia> findAll();
	
	void delete(Long id);
	
	Qingjia findOne(Long id);


}
