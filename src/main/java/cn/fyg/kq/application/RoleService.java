package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.modmenu.role.Role;

public interface RoleService {
	
	List<Role> findAll();

	boolean exists(String key);

	Role create();

	Role save(Role role);
	
	Role find(String key);

	void delete(String key);

}
