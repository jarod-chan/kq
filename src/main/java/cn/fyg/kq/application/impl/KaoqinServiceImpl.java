package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.kaoqin.KaoqinCommitVld;
import cn.fyg.kq.domain.model.kaoqin.KaoqinNo;
import cn.fyg.kq.domain.model.kaoqin.KaoqinRepository;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.nogenerator.generator.Pattern;
import cn.fyg.kq.domain.model.nogenerator.generator.PatternFactory;
import cn.fyg.kq.domain.model.nogenerator.look.Lock;
import cn.fyg.kq.domain.model.nogenerator.look.LockService;
import cn.fyg.kq.domain.model.nogenerator.service.GeneService;
import cn.fyg.kq.domain.shared.verify.Result;



@Service("kaoqinService")
public class KaoqinServiceImpl extends SericeQueryImpl<Kaoqin>  implements KaoqinService {
	
	@Autowired
	KaoqinRepository kaoqinRepository;
	@Autowired
	LockService lockService;
	@Autowired
	@Qualifier("kaoqinNo")
	PatternFactory<Kaoqin> noFactory;
	@Autowired
	KaoqinServiceImplExd kaoqinServiceImplExd;
	@Autowired
	GeneService geneService;


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
	public Kaoqin save(Kaoqin kaoqin) {
		noFactory=new KaoqinNo();
		Pattern<Kaoqin> pattern = noFactory.create(kaoqin).setEmpty(kaoqin.getId()!=null);
			
		Lock lock = this.lockService.getLock(pattern);
		lock.lock();
		try{
			return this.kaoqinServiceImplExd.save(kaoqin, pattern);
		}finally{
			lock.unlock();
		}	
	}
	
	@Transactional
	public Kaoqin save(Kaoqin kaoqin,Pattern<Kaoqin> pattern) {
		this.geneService.generateNextNo(pattern);
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

	@Override
	public JpaSpecificationExecutor<Kaoqin> getSpecExecutor() {
		return this.kaoqinRepository;
	}

	@Override
	public Result verifyForCommit(Kaoqin kaoqin) {
		KaoqinCommitVld vld = new KaoqinCommitVld();
		vld.setValObject(kaoqin);
		return vld.verify();
	}

}
