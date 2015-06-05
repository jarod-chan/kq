package cn.fyg.zktime.domain.monthcheck;

/**
 *查询monthcheck所需要的参数
 */
public class Param {
	
	private int userid;
	
	private int year;
	
	private int month;
	
	
	public Param(int userid, int year, int month) {
		super();
		this.userid = userid;
		this.year = year;
		this.month = month;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
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
