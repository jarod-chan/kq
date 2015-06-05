package cn.fyg.zktime.domain.monthcheck;

import java.util.ArrayList;
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
	
	private List<SchclassInOut> schclassInOuts;//当天班次

	private List<Checkinout> checkinout;//打卡时间

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<SchclassInOut> getSchclassInOuts() {
		return schclassInOuts;
	}

	public void setSchclassInOuts(List<SchclassInOut> schclassInOuts) {
		this.schclassInOuts = schclassInOuts;
	}

	public List<Checkinout> getCheckinout() {
		return checkinout;
	}

	public void setCheckinout(List<Checkinout> checkinout) {
		this.checkinout = checkinout;
	}
	
	public void compareCheckinout(){
		for (Checkinout checkinout : this.checkinout) {
			for (SchclassInOut schclassInOut : this.schclassInOuts) {
				Schclass schclass = schclassInOut.getSchclass();
				Date checktime =DateUtil.dateDateZero(checkinout.getChecktime());
				if(DateUtil.inDate(checktime,schclass.getCheckintime1(),schclass.getCheckintime2())){
					schclassInOut.setCheckin(true);
				}
				if(DateUtil.inDate(checktime,schclass.getCheckouttime1(),schclass.getCheckouttime2())){
					schclassInOut.setCheckout(true);
				}
			}
		}
	}

	public void setSchclassesToInOut(List<Schclass> schclasses) {
		this.schclassInOuts = new ArrayList<SchclassInOut>();
		for (Schclass schclass : schclasses) {
			this.schclassInOuts.add(new SchclassInOut(schclass));
		}
	}
	


}
