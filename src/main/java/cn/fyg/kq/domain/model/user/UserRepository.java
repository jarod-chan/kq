package cn.fyg.kq.domain.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<User, String>, JpaSpecificationExecutor<User> {
	
	User save(User user);
	
	List<User> findAll();
	
	void delete(String fid);
	
	User findOne(String fid);

}
