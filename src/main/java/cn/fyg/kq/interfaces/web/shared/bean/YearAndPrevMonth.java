package cn.fyg.kq.interfaces.web.shared.bean;

import cn.fyg.kq.interfaces.web.shared.tool.DateTool;

public class YearAndPrevMonth {
	
	private Long year;
	private Long month;
	
	public YearAndPrevMonth() {
		DateTool dtl=new DateTool();
		this.year=dtl.getPrevMonthYear();
		this.month=dtl.getPrevMonth();
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}
	
	

}
