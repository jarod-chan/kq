package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.user.User;

public interface CheckuserService  extends ServiceQuery<Checkuser> {
	
	Checkuser create(User user);
	
	Checkuser save(Checkuser checkuser);
	
	List<Checkuser> findAll();
	
	void delete(Long id);
	
	Checkuser find(Long id);

}
