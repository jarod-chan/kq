package cn.fyg.kq.interfaces.web.modify.dto;

import java.util.Date;
import java.util.List;

import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.Schclass;

public class DateCheckDao {
	
	private Date date;//日期
	
	private Schclass schclasses;//当天班次

	private List<Checkinout> checkinout;//打卡时间
	
	private int len=0;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Schclass getSchclasses() {
		return schclasses;
	}

	public void setSchclasses(Schclass schclasses) {
		this.schclasses = schclasses;
	}

	public List<Checkinout> getCheckinout() {
		return checkinout;
	}

	public void setCheckinout(List<Checkinout> checkinout) {
		this.checkinout = checkinout;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
	
	

}
