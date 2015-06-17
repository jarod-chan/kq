package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.domain.shared.kq.Comp;

public interface ReptlineService extends ServiceQuery<Reptline> {
	
	Reptline create(Comp comp);
	
	Reptline save(Reptline reptline);
	
	List<Reptline> findAll();
	
	void delete(Long id);
	
	Reptline find(Long id);
	
	Reptline findByUser_fidAndLevel(String fid,int level);
	
	Reptline findByCode(String code);
	
	Reptline maxUserLevel(String fid);
	

}
