package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.user.User;

public interface UserService extends ServiceQuery<User>{
	
	User create();
	
	User save(User user);
	
	User find(String fid);
	
	void delete(String fid);
		
	List<User> findAll();
	
	boolean exists(String fid);
	
	User findByFnumber(String fnumber);

}
