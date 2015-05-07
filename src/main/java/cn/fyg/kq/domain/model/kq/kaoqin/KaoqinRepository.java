package cn.fyg.kq.domain.model.kq.kaoqin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface KaoqinRepository extends Repository<Kaoqin, Long>, JpaSpecificationExecutor<Kaoqin> {
	
	Kaoqin save(Kaoqin kaoqin);
	
	List<Kaoqin> findAll();
	
	void delete(Long id);
	
	Kaoqin findOne(Long id);

}
