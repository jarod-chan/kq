package cn.fyg.kq.domain.model.md;

import java.util.Date;

public class KQUserFactory {
	
	public static KQUser create(String fid){
		KQUser kqUser = new KQUser();
		kqUser.setFid(fid);
		kqUser.setCreatetime(new Date());
		return kqUser;
	}

}
