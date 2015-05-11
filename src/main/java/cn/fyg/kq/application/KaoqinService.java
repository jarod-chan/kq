package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kq.qingjia.Qingjia;

public interface KaoqinService {
	
	Kaoqin create();
	
	List<Kaoqin> findAll();
	
	Kaoqin save(Kaoqin kaoqin);

	Kaoqin find(Long kaoqinId);

	void delete(Long kaoqinId);

}
