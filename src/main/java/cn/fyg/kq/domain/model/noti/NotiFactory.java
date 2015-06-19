package cn.fyg.kq.domain.model.noti;

import cn.fyg.kq.domain.model.user.User;

public class NotiFactory {
	
	public static Noti create(User user,String message){
		Noti noti = new Noti();
		noti.setNoti(message);
		noti.setReceiver(user);
		noti.setStat(ReadStat.n);
		return noti;
	}

}
