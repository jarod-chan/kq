package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.PeriodService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodFactory;
import cn.fyg.kq.domain.model.period.PeriodRepository;
import cn.fyg.kq.domain.shared.kq.Comp;

@Service
public class PeriodServiceImpl extends SericeQueryImpl<Period> implements PeriodService {
	
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

	@Override
	public boolean exist(MonthItem monthItem, Comp comp) {
		List<Period> periodList=this.periodRepository.findByMonthitem_YearAndMonthitem_MonthAndComp(monthItem.getYear(),monthItem.getMonth(),comp);
		if(periodList.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public JpaSpecificationExecutor<Period> getSpecExecutor() {
		return this.periodRepository;
	}

	@Override
	public Period create(MonthItem monthItem,Comp comp) {
		return PeriodFactory.create(monthItem,comp);
	}

}
