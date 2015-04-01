package cn.fyg.kq.domain.model.vacation.leave;

import cn.fyg.kq.domain.model.shared.NoComputer;
import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.common.LeaveType;
import cn.fyg.module.user.User;

public class LeaveFactory {
	
	public static Leave create(User user, String maxNo) {
		Leave leave=new Leave();
		String no=NoComputer.computeNo(Leave.BUSINESS_CODE, user, maxNo);
		leave.setNo(no);
		leave.setLeaveType(LeaveType.personal);
		leave.setBusiState(BusiState.new_);
		leave.setUser(user);
		return leave;
	}

}
