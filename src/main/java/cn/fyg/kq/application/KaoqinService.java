package cn.fyg.kq.application;

import cn.fyg.kq.application.common.CommitValidator;
import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;

public interface KaoqinService extends ServiceQuery<Kaoqin>,CommitValidator<Kaoqin> {
	
	Kaoqin create();
	
	Kaoqin saveTemp(Kaoqin kaoqin);
	
	Kaoqin save(Kaoqin kaoqin);

	Kaoqin find(Long kaoqinId);

	void delete(Long kaoqinId);



}
