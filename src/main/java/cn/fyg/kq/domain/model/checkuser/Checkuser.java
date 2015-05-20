package cn.fyg.kq.domain.model.checkuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.user.User;

/**
 *参与考勤的用户
 */
@Entity
@Table(name="bs_checkuser")
public class Checkuser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键	
	
	@Column(insertable=false, updatable=false)
	private String fid;//	easid
	
	private String comp;//	公司主键
	
	@Enumerated(EnumType.STRING)
	private Kqstat kqstat;//是否考勤
	
	private Integer userid;//	考勤机用户id
	
	private String badgenumber;//	考勤机工号
	
	private String name;//	考勤机姓名
	
	@OneToOne
	@JoinColumn(name="fid")
	User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public Kqstat getKqstat() {
		return kqstat;
	}

	public void setKqstat(Kqstat kqstat) {
		this.kqstat = kqstat;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
