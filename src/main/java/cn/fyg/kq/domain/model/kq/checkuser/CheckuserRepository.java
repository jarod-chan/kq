package cn.fyg.kq.domain.model.kq.checkuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface CheckuserRepository extends Repository<Checkuser, Long>, JpaSpecificationExecutor<Checkuser> {
	
	Checkuser save(Checkuser checkuser);
	
	List<Checkuser> findAll();
	
	void delete(Long id);
	
	Checkuser findOne(Long id);

}
