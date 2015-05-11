package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kq.kaoqin.KaoqinRepository;
import cn.fyg.kq.domain.model.kq.qingjia.Qingjia;

@Service
public class KaoqinServiceImpl implements KaoqinService {
	
	@Autowired
	KaoqinRepository kaoqinRepository;

	@Override
	public Kaoqin create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kaoqin> findAll() {
		return this.kaoqinRepository.findAll();
	}

	@Override
	@Transactional
	public Kaoqin save(Kaoqin kaoqin) {
		return this.kaoqinRepository.save(kaoqin);
	}

	@Override
	public Kaoqin find(Long kaoqinId) {
		return this.kaoqinRepository.findOne(kaoqinId);
	}

	@Override
	@Transactional
	public void delete(Long kaoqinId) {
		this.kaoqinRepository.delete(kaoqinId);
	}

}
