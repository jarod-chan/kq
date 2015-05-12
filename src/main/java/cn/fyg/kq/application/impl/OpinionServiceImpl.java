package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.opinion.OpinionRepository;

@Service("opinionService")
public class OpinionServiceImpl implements OpinionService {

	@Autowired
	OpinionRepository opinionRepository;
	
	@Override
	@Transactional
	public Opinion append(Opinion opinion) {
		return opinionRepository.save(opinion);
	}

	@Override
	public List<Opinion> allOpinion(String businessCode, Long businessId) {
		return opinionRepository.findByBusinessCodeAndBusinessIdOrderByIdAsc(businessCode, businessId);
	}

	@Override
	@Transactional
	public void clear(String businessCode, Long businessId) {
		opinionRepository.deleteByBusinessCodeAndBusinessId(businessCode, businessId);
	}

}
