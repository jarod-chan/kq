package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kq.qingjia.Qingjia;

public interface KaoqinService {
	
	Kaoqin create();
	
	List<Kaoqin> findAll();
	
	Qingjia save(Kaoqin kaoqin);

	Qingjia find(Long kaoqinId);

	void delete(Long kaoqinId);

}
