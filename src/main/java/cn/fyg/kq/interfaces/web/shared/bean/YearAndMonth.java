package cn.fyg.kq.interfaces.web.shared.bean;

import cn.fyg.kq.interfaces.web.shared.tool.DateTool;



public class YearAndMonth {
	private Long year;
	private Long month;
	
	

	public YearAndMonth() {
		DateTool dtl=new DateTool();
		this.year=dtl.getCurrentYear();
		this.month=dtl.getCurrentMonth();
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
