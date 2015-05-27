package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.qingjia.Qingjia;
import cn.fyg.kq.domain.model.qingjia.QingjiaState;
import cn.fyg.kq.domain.model.user.User;

public interface QingjiaService {
	
	Qingjia create(User user,QingjiaState state,String comp);
	
	List<Qingjia> findAll();
	
	Qingjia save(Qingjia qingjia);

	Qingjia find(Long qingjiaId);

	void delete(Long qingjiaId);

}
