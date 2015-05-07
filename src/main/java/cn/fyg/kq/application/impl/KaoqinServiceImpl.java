package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Qingjia save(Kaoqin kaoqin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qingjia find(Long kaoqinId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long kaoqinId) {
		// TODO Auto-generated method stub

	}

}
