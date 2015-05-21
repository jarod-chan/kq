package cn.fyg.kq.domain.model.kaoqin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;


public interface KaoqinRepository extends Repository<Kaoqin, Long>, JpaSpecificationExecutor<Kaoqin> {
	
	Kaoqin save(Kaoqin kaoqin);
	
	List<Kaoqin> findAll();
	
	void delete(Long id);
	
	Kaoqin findOne(Long id);

}
