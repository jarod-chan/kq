package cn.fyg.kq.domain.model.kaoqin.busi;

import javax.persistence.Embeddable;

@Embeddable
public class MonthItem {
	
	private int year;//年份
	
	private int month;//月份

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	

}
