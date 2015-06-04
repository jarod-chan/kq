package cn.fyg.zktime.domain.monthcheck;

import java.util.Date;
import java.util.List;

import cn.fyg.kq.infrastructure.tool.date.DateUtil;
import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.Schclass;

/**
 *某一日起的具体打卡时段和打卡时间
 */
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
				Date checktime =DateUtil.dateDateZero(checkinout.getChecktime());
				if(DateUtil.inDate(checktime,schclass.getCheckintime1(),schclass.getCheckintime2())){
					schclass.setCheckin(true);
				}
				if(DateUtil.inDate(checktime,schclass.getCheckouttime1(),schclass.getCheckouttime2())){
					schclass.setCheckout(true);
				}
			}
		}
	}
	


}
