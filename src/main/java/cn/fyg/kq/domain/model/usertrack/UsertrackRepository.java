package cn.fyg.kq.domain.model.usertrack;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface UsertrackRepository extends Repository<Usertrack, Long> {
	
	Usertrack save(Usertrack usertrack);
	
	void delete(Usertrack usertrack);
	
	Usertrack findByUserIdAndProcessInstanceId(String userId,String processInstanceId);
	
	List<Usertrack> findByUserIdOrderByIdAsc(String userId);

}
