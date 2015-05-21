package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;

public interface KaoqinService extends ServiceQuery<Kaoqin> {
	
	Kaoqin create();
	
	List<Kaoqin> findAll();
	
	Kaoqin save(Kaoqin kaoqin);

	Kaoqin find(Long kaoqinId);

	void delete(Long kaoqinId);

}
