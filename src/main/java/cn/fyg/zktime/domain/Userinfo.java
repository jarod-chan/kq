package cn.fyg.zktime.domain;


/**
 *用户信息
 */
public class Userinfo {
	
	private int userid;
	
	private String badgenumber;
	
	private String name;
	
	public String getBadgenumber() {
		return badgenumber;
	}

	public void setBadgenumber(String badgenumber) {
		this.badgenumber = badgenumber;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Userinfo [userid=" + userid + ", name=" + name + "]";
	}

	
	
	

}
