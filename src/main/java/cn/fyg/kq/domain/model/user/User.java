package cn.fyg.kq.domain.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fyg.kq.domain.model.role.Role;

@Entity
@Table(name="kq_user")
public class User {
	
	@Id
	@Column(name="fid")
	private String fid;//easid
	
	private String fnumber;//用户名
	
	private String fname;//用户实名
	
	@ManyToOne(targetEntity=Role.class)
	@JoinColumn(name="role_key")
	private Role role;//角色
	
	@Column(name="createtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;//创建时间
	
	@PrePersist
	private void prePersist(){
		this.createtime=new Date();
	}	

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



}
