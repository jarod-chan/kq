package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.md.KQUser;

public interface KQUserService {
	
	void init(String fid,String fnumber,String fname,int userid) throws Exception;
	
	boolean isInit(String fid);
	
	List<KQUser> all();

	void delete(String fid);
}
