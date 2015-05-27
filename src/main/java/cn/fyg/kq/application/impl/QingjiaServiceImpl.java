package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.QingjiaService;
import cn.fyg.kq.domain.model.qingjia.Qingjia;
import cn.fyg.kq.domain.model.qingjia.QingjiaFactory;
import cn.fyg.kq.domain.model.qingjia.QingjiaRepository;
import cn.fyg.kq.domain.model.qingjia.QingjiaState;
import cn.fyg.kq.domain.model.user.User;

@Repository
public class QingjiaServiceImpl implements QingjiaService {

	@Autowired
	QingjiaRepository qingjiaRepository;
	
	@Override
	public List<Qingjia> findAll() {
		return this.qingjiaRepository.findAll();
	}

	@Override
	@Transactional
	public Qingjia save(Qingjia qingjia) {
		return this.qingjiaRepository.save(qingjia);
	}

	@Override
	public Qingjia create(User user,QingjiaState state,String comp) {
		return QingjiaFactory.create(user,state,comp);
	}

	@Override
	public Qingjia find(Long qingjiaId) {
		return this.qingjiaRepository.findOne(qingjiaId);
	}

	@Override
	@Transactional
	public void delete(Long qingjiaId) {
		this.qingjiaRepository.delete(qingjiaId);
		
	}

}
