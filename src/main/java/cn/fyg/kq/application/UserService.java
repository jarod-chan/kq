package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.user.User;

public interface UserService {
	
	User save(User user);
	
	List<User> findAll();
	
	void delete(String fid);
	
	User find(String fid);

}
