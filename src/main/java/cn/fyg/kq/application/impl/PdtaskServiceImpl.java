package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.PdtaskService;
import cn.fyg.kq.domain.model.pdtask.Pdtask;
import cn.fyg.kq.domain.model.pdtask.PdtaskFactory;
import cn.fyg.kq.domain.model.pdtask.PdtaskRepository;
import cn.fyg.kq.domain.model.pdtask.TaskState;

@Service
public class PdtaskServiceImpl implements PdtaskService {
	
	@Autowired
	PdtaskRepository pdtaskRepository;

	@Override
	public Pdtask create(Long period_id, String taskname) {
		return PdtaskFactory.create(period_id, taskname);
	}

	@Override
	@Transactional
	public void append(Pdtask pdtask) {
		this.pdtaskRepository.save(pdtask);
	}

	@Override
	@Transactional
	public void startPeriodTask(Long period_id) {
		Pdtask pdtask = this.pdtaskRepository.findOne(period_id);
		pdtask.setState(TaskState.start);
	}

	@Override
	@Transactional
	public void endPeriodTask(Long period_id) {
		this.pdtaskRepository.delete(period_id);
	}

	@Override
	@Transactional
	public void cancelPeriodTaks(Long period_id) {
		this.pdtaskRepository.delete(period_id);
	}

	@Override
	public boolean havePeriodTask(Long period_id) {
		return this.pdtaskRepository.exists(period_id);
	}

	@Override
	public Pdtask get(Long period_id) {
		return this.pdtaskRepository.findOne(period_id);
	}

}
