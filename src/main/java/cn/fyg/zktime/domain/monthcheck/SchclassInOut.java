package cn.fyg.zktime.domain.monthcheck;

import cn.fyg.zktime.domain.Schclass;

public class SchclassInOut {
	
	private Schclass schclass;
	
	private boolean checkin=false;//是否签到
	
	private boolean checkout=false;//是否签退

	public Schclass getSchclass() {
		return schclass;
	}

	public void setSchclass(Schclass schclass) {
		this.schclass = schclass;
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

	public SchclassInOut(Schclass schclass) {
		super();
		this.schclass = schclass;
	}
	
}
