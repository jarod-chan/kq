package cn.fyg.kq.domain.model.kq.admincomp;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface AdmincompRepository extends Repository<Admincomp,String>  {
	
	List<Admincomp> findAll();

}
