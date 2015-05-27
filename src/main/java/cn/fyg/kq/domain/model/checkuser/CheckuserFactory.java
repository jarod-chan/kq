package cn.fyg.kq.domain.model.checkuser;

import cn.fyg.kq.domain.model.user.User;

public class CheckuserFactory {

	public static Checkuser create(User user) {
		Checkuser checkuser = new Checkuser();
		checkuser.setFid(user.getFid());
		checkuser.setUser(user);
		checkuser.setKqstat(Kqstat.todo);
		return checkuser;
	}

}
