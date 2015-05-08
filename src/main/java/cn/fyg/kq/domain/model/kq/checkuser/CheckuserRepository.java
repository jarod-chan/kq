package cn.fyg.kq.domain.model.kq.checkuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface CheckuserRepository extends Repository<Checkuser, String>, JpaSpecificationExecutor<Checkuser> {
	
	Checkuser save(Checkuser checkuser);
	
	List<Checkuser> findAll();
	
	void delete(String fid);
	
	Checkuser findOne(String fid);

}
