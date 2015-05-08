package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.domain.model.kq.period.Period;
import cn.fyg.kq.domain.model.kq.period.PeriodRepository;

@Service
public class PeriodServiceImpl implements PeriodService {
	
	@Autowired
	PeriodRepository periodRepository;

	@Override
	@Transactional
	public Period save(Period period) {
		return this.periodRepository.save(period);
	}

	@Override
	public List<Period> findAll() {
		return this.periodRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.periodRepository.delete(id);
	}

	@Override
	public Period find(Long id) {
		return this.periodRepository.findOne(id);
	}

}
