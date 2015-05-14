package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.domain.model.reptline.ReptlineRepository;

@Service
public class ReptlineServiceImpl implements ReptlineService {
	
	@Autowired
	ReptlineRepository reptlineRepository;

	@Override
	@Transactional
	public Reptline save(Reptline reptline) {
		return reptlineRepository.save(reptline);
	}

	@Override
	public List<Reptline> findAll() {
		return reptlineRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		reptlineRepository.delete(id);
	}

	@Override
	public Reptline find(Long id) {
		return reptlineRepository.findOne(id);
	}


	

}
