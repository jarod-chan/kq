package cn.fyg.kq.application;

import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.module.user.User;

public interface LeaveService {
	
	Leave create(User user);
	
	Leave save(Leave leave);
	
	Leave find(Long id);
	
}
