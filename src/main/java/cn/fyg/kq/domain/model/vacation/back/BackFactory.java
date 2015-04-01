package cn.fyg.kq.domain.model.vacation.back;

import cn.fyg.kq.domain.model.shared.NoComputer;
import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.common.LeaveType;
import cn.fyg.module.user.User;

public class BackFactory {
	
	public static Back create(User user,String maxNo){
		Back back=new Back();
		String no=NoComputer.computeNo(Back.BUSINESS_CODE, user, maxNo);
		back.setNo(no);
		back.setLeaveType(LeaveType.personal);
		back.setBusiState(BusiState.new_);
		back.setUser(user);
		return back;
	}

}
