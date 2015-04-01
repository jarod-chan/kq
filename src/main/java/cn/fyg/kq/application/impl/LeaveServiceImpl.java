package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.LeaveService;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.kq.domain.model.vacation.leave.LeaveFactory;
import cn.fyg.kq.domain.model.vacation.leave.LeaveRepository;
import cn.fyg.module.user.User;
import cn.fyg.module.user.UserService;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
	
	
	@Autowired
	LeaveRepository leaveRepository;
	@Autowired
	UserService service;

	@Override
	public Leave create(User user) {
		String maxNo=leaveRepository.findMaxNoByUser(user);
		return LeaveFactory.create(user, maxNo);
	}



	@Override
	@Transactional
	public Leave save(Leave leave) {
		return leaveRepository.save(leave);
	}

	@Override
	public Leave find(Long id) {
		return leaveRepository.findOne(id);
	}


}
