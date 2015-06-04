package cn.fyg.zktime.domain;

import java.util.Date;

/**
 *时段
 */
public class Schclass {
	
	private int schclassid;//班次主键
	
	private String schname;//班次名称
	
	private Date starttime;//开始时间
	
	private Date endtime;//结束时间
	
	private Date checkintime1;//开始签到时间
	
	private Date checkintime2;//结束签退时间
	
	private Date checkouttime1;//开始签退时间
	
	private Date checkouttime2;//结束签退时间
	
	private boolean checkin=false;//是否签到
	
	private boolean checkout=false;//是否签退

	public int getSchclassid() {
		return schclassid;
	}

	public void setSchclassid(int schclassid) {
		this.schclassid = schclassid;
	}

	public String getSchname() {
		return schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getCheckintime1() {
		return checkintime1;
	}

	public void setCheckintime1(Date checkintime1) {
		this.checkintime1 = checkintime1;
	}

	public Date getCheckintime2() {
		return checkintime2;
	}

	public void setCheckintime2(Date checkintime2) {
		this.checkintime2 = checkintime2;
	}

	public Date getCheckouttime1() {
		return checkouttime1;
	}

	public void setCheckouttime1(Date checkouttime1) {
		this.checkouttime1 = checkouttime1;
	}

	public Date getCheckouttime2() {
		return checkouttime2;
	}

	public void setCheckouttime2(Date checkouttime2) {
		this.checkouttime2 = checkouttime2;
	}


	public boolean isCheckin() {
		return checkin;
	}

	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	public boolean isCheckout() {
		return checkout;
	}

	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}
	
	
	

}
