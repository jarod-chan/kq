package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.checkuser.Checkuser;

public interface CheckuserService {
	
	Checkuser save(Checkuser checkuser);
	
	List<Checkuser> findAll();
	
	void delete(String fid);
	
	Checkuser find(String fid);

}
