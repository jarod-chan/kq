package cn.fyg.kq.domain.model.reptline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface ReptlineRepository extends Repository<Reptline, Long>, JpaSpecificationExecutor<Reptline> {
	
	Reptline save(Reptline reptline);
	
	List<Reptline> findAll();
	
	void delete(Long id);
	
	Reptline findOne(Long id);
	
	Reptline findByUser_fidAndLevel(String fid,int level);
	
	Reptline findByCode(String code);

}