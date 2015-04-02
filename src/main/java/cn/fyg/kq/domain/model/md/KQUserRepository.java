package cn.fyg.kq.domain.model.md;

import org.springframework.data.repository.Repository;

public interface KQUserRepository extends Repository<KQUser, String> {

	KQUser save(KQUser kqUser);
	
	KQUser findOne(String fid);
	
	boolean exists(String  fid);
	
}
