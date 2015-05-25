package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.kq.application.RoleService;
import cn.fyg.kq.domain.model.role.Role;
import cn.fyg.kq.domain.model.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return this.roleRepository.findAll();
	}

}
