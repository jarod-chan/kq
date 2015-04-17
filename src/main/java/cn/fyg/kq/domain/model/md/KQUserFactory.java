package cn.fyg.kq.domain.model.md;

import java.util.Date;

public class KQUserFactory {
	
	public static KQUser create(String fid,String fnumber,String fname){
		KQUser kqUser = new KQUser();
		kqUser.setFid(fid);
		kqUser.setFnumber(fnumber);
		kqUser.setFname(fname);
		kqUser.setCreatetime(new Date());
		return kqUser;
	}

}
