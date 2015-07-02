package cn.fyg.kq.application;

import cn.fyg.kq.domain.model.pdtask.Pdtask;

public interface PdtaskService {
	
	Pdtask create(Long period_id,String taskname);
	
	Pdtask get(Long period_id);
	
	void append(Pdtask pdtask);

	void startPeriodTask(Long period_id);
	
	void endPeriodTask(Long period_id);
	
	void cancelPeriodTaks(Long period_id);
	
	boolean havePeriodTask(Long period_id);
	
	
}
