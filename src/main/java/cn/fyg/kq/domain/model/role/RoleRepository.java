package cn.fyg.kq.domain.model.role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,String> {
	
	List<Role> findAll();
	
}
