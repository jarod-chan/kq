package cn.fyg.zktime.domain.monthcheck;

import java.util.List;

/**
 *月考勤记录
 */
public class MonthCheck {
	
	private List<DateCheck> datechecks;

	public List<DateCheck> getDatechecks() {
		return datechecks;
	}

	public void setDatechecks(List<DateCheck> datechecks) {
		this.datechecks = datechecks;
	}
	
	/*
	 * 完成初始化
	 */
	public void finishInit(){
		for(DateCheck dateCheck:this.getDatechecks()){
			dateCheck.compareCheckinout();
		}
	}

}
