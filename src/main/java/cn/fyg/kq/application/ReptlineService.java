package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.reptline.Reptline;

public interface ReptlineService {
	
	Reptline save(Reptline reptline);
	
	List<Reptline> findAll();
	
	void delete(Long id);
	
	Reptline find(Long id);
	
	Reptline findByUser_fidAndLevel(String fid,int level);
	
	Reptline findByCode(String code);

}
