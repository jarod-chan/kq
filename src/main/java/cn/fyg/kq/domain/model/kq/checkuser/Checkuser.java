package cn.fyg.kq.domain.model.kq.checkuser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.kq.user.User;

/**
 *参与考勤的用户
 */
@Entity
@Table(name="bs_checkuser")
public class Checkuser {
	
	@Id
	private String fid;//	easid
	
	private int userid;//	考勤机用户id
	
	private String badgenumber;//	考勤机工号
	
	private String name;//	考勤机姓名
	
	private String comp;//	公司主键
	
	@OneToOne
	//@MapsId
	@JoinColumn(name="fid")
	User user;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBadgenumber() {
		return badgenumber;
	}

	public void setBadgenumber(String badgenumber) {
		this.badgenumber = badgenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	


}
