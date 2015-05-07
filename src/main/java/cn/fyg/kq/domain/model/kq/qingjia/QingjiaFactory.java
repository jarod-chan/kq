package cn.fyg.kq.domain.model.kq.qingjia;

import cn.fyg.kq.domain.model.kq.user.User;

public class QingjiaFactory {
	
	public static Qingjia create(User user,QingjiaState state,String comp){
		Qingjia qingjia = new Qingjia();
		qingjia.setUser(user);
		qingjia.setState(state);
		qingjia.setComp(comp);
		return qingjia;
	}

}
