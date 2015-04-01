package cn.fyg.kq.application;

import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.module.user.User;

public interface BackService {
	
	Back create(User user);
	
	Back save(Back back);
	
	Back find(Long id);
		
}
