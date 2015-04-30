package cn.fyg.zktime.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateCheck {
	
	private Date date;//日期
	
	private List<Schclass> schclasses;//当天班次

	private List<Checkinout> checkinout;//打卡时间

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Schclass> getSchclasses() {
		return schclasses;
	}

	public void setSchclasses(List<Schclass> schclasses) {
		this.schclasses = schclasses;
	}

	public List<Checkinout> getCheckinout() {
		return checkinout;
	}

	public void setCheckinout(List<Checkinout> checkinout) {
		this.checkinout = checkinout;
	}
	
	public void compareCheckinout(){
		for (Checkinout checkinout : this.checkinout) {
			for (Schclass schclass : this.schclasses) {
				Date checktime =this.to1900_1_1(checkinout.getChecktime());
				if(checktimeIn(checktime,schclass.getCheckintime1(),schclass.getCheckintime2())){
					schclass.setCheckin(true);
				}
				if(checktimeIn(checktime,schclass.getCheckouttime1(),schclass.getCheckouttime2())){
					schclass.setCheckout(true);
				}
			}
		}
	}
	
	private boolean checktimeIn(Date checktime,Date begin,Date end){
		return checktime.compareTo(begin)>=0
				&&checktime.compareTo(end)<=0;
	}
	
	private Date to1900_1_1(Date Date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(Date);
		calendar.set(Calendar.YEAR, 1900);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
}
