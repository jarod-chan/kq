package cn.fyg.kq.domain.model.kq.admincomp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.kq.tag.Tag;
import cn.fyg.kq.domain.model.user.User;

@Entity
@Table(name="bs_admincomp")
public class Admincomp {
	
	@Id
	@Column(name="fid")
	private String fid;//easid
	
//	private String admincomp;//管理的公司
	
	@Enumerated(EnumType.STRING)
	private IskqEnum iskq;//是否参与考勤
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="fid", referencedColumnName="fid")
	User user;
	
	@OneToOne(targetEntity = Tag.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "admincomp")
	Tag tag;
	

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
//
//	public String getAdmincomp() {
//		return admincomp;
//	}
//
//	public void setAdmincomp(String admincomp) {
//		this.admincomp = admincomp;
//	}

	public IskqEnum getIskq() {
		return iskq;
	}

	public void setIskq(IskqEnum iskq) {
		this.iskq = iskq;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} 
	
}
