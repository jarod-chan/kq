package cn.fyg.kq.application;

import cn.fyg.kq.domain.model.vacation.common.Dayitem;
import cn.fyg.kq.domain.model.vacation.compdate.Compdate;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;

public interface CompdateService {
	
	Compdate append(Dayitem dayitem);
	
	void remove(Dayitem dayitem);

	void syncCompdate();
	
	void syncCompdateYear(Long year);

	DayResult computerDay(Dayitem begDayitem,Dayitem endDayitem);
}
