package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.kq.application.AdmincompService;
import cn.fyg.kq.domain.model.kq.admincomp.Admincomp;
import cn.fyg.kq.domain.model.kq.admincomp.AdmincompRepository;

@Service
public class AdmincompServiceImpl implements AdmincompService {
	
	@Autowired
	AdmincompRepository admincompRepository;

	@Override
	public List<Admincomp> findAll() {
		return this.admincompRepository.findAll();
	}

}
