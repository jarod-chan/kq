package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.domain.model.kaoqin.KaoqinRepository;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.nogenerator.generator.Pattern;
import cn.fyg.kq.domain.model.nogenerator.service.GeneService;

@Component("kaoqinServiceImplExd")
public class KaoqinServiceImplExd {
	
	@Autowired
	GeneService geneService;
	@Autowired
	KaoqinRepository kaoqinRepository;
	
	@Transactional
	public Kaoqin save(Kaoqin kaoqin,Pattern<Kaoqin> pattern) {
		this.geneService.generateNextNo(pattern);
		return this.kaoqinRepository.save(kaoqin);
	}

}
