package cn.fyg.zktime.domain;


/**
 *用户信息
 */
public class Userinfo {
	
	private int userid;
	
	private String name;

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
