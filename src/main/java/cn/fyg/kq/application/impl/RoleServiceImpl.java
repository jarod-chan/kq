package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.RoleService;
import cn.fyg.kq.domain.model.modmenu.role.Role;
import cn.fyg.kq.domain.model.modmenu.role.RoleFactory;
import cn.fyg.kq.domain.model.modmenu.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return this.roleRepository.findAll();
	}

	@Override
	public boolean exists(String key) {
		return this.roleRepository.exists(key);
	}

	@Override
	public Role create() {
		return RoleFactory.create();
	}

	@Override
	@Transactional
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public Role find(String key) {
		return this.roleRepository.findOne(key);
	}

	@Override
	public void delete(String key) {
		this.roleRepository.delete(key);
	}

}
