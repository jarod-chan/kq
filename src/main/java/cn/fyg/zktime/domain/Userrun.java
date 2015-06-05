package cn.fyg.zktime.domain;

import java.util.Date;

/**
 *用户在某一时间的班次
 */
public class Userrun {
	
	private int userid;//用户id
	
	private Date startdate;//开始日期
	
	private Date enddate;//结束日期
	
	private int num_of_run_id;//班次id

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getNum_of_run_id() {
		return num_of_run_id;
	}

	public void setNum_of_run_id(int num_of_run_id) {
		this.num_of_run_id = num_of_run_id;
	}
	
}
