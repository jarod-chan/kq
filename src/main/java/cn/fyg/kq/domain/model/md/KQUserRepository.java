package cn.fyg.kq.domain.model.md;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

public interface KQUserRepository extends Repository<KQUser, String> {

	KQUser save(KQUser kqUser);
	
	KQUser findOne(String fid);
	
	boolean exists(String  fid);

	List<KQUser>  findAll(Sort sort);
	
	void delete(String  fid);
	
}
