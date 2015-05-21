package cn.fyg.kq.domain.model.nogenerator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.domain.model.nogenerator.generator.Pattern;
import cn.fyg.kq.domain.model.nogenerator.norecord.NoKey;
import cn.fyg.kq.domain.model.nogenerator.norecord.NoNotLastException;
import cn.fyg.kq.domain.model.nogenerator.norecord.NoRecord;
import cn.fyg.kq.domain.model.nogenerator.norecord.NoRecordRepository;
import cn.fyg.kq.domain.model.nogenerator.service.GeneService;

@Component("geneService")
public class GeneServiceImpl implements GeneService {
	
	@Autowired
	NoRecordRepository noRecordRepository;

	@Override
	public void generateNextNo(Pattern<? extends Object> pattern) {
		if(pattern.isEmpty()) return;
		NoKey noKey = pattern.getNoKey();
		if(!this.noRecordRepository.exists(noKey)){
			NoRecord noRecord=new NoRecord(noKey,0L,pattern.getLimmit());
			this.noRecordRepository.save(noRecord);
		}
		NoRecord noRecord=this.noRecordRepository.findOne(noKey);
		pattern.setNo(noRecord.nextNo());
		
	}

	@Override
	public void rollbackLastNo(Pattern<? extends Object> pattern)
			throws NoNotLastException {
		if(pattern.isEmpty()) return;
		NoKey noKey = pattern.getNoKey();
		NoRecord noRecord=this.noRecordRepository.findOne(noKey);
		noRecord.prevNo(pattern.getNo());
	}


}
