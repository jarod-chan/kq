package cn.fyg.kq.application.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.ExcludeService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.exclude.Exclude;
import cn.fyg.kq.domain.model.exclude.ExcludeFactory;
import cn.fyg.kq.domain.model.exclude.ExcludeRepository;
import cn.fyg.kq.domain.model.exclude.ExcludeSpecs;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.shared.kq.Ampm;

@Service
public class ExcludeServiceImpl extends SericeQueryImpl<Exclude> implements ExcludeService {
	
	@Autowired
	ExcludeRepository excludeRepository;

	@Override
	public JpaSpecificationExecutor<Exclude> getSpecExecutor() {
		return this.excludeRepository;
	}

	@Override
	public Exclude create(Long period_id, Date date, Ampm ampm) {
		return ExcludeFactory.create(period_id, date, ampm);
	}

	@Override
	public List<Exclude> periodExclude(Period period) {
		Long periodId=period.getId();
		Specification<Exclude> ofPeriod = ExcludeSpecs.ofPeriod(periodId);
		Specifications<Exclude> specs = Specifications.where(ofPeriod);
		Sort sort = new Sort(new Order("dayitem.date"),new Order("dayitem.ampm"));
		return this.findAll(specs, sort);
	}

	@Override
	@Transactional
	public Exclude save(Exclude exclude) {
		return this.excludeRepository.save(exclude);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.excludeRepository.delete(id);
	}

}
