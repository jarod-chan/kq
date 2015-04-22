package cn.fyg.zktime.domain;

import java.util.Date;

/**
 *考情时间
 */
public class Checkinout {
	
	private int id;
	
	private Date checktime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

}
