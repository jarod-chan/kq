package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.BackService;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.kq.domain.model.vacation.back.BackFactory;
import cn.fyg.kq.domain.model.vacation.back.BackRepository;
import cn.fyg.module.user.User;

@Service("backService")
public class BackServiceImpl implements BackService {
	
	@Autowired
	BackRepository backRepository;

	@Override
	public Back create(User user) {
		String maxNo=backRepository.findMaxNoByUser(user);
		return BackFactory.create(user, maxNo);
	}

	@Override
	@Transactional
	public Back save(Back back) {
		return backRepository.save(back);
	}

	@Override
	public Back find(Long id) {
		return backRepository.findOne(id);
	}

}
