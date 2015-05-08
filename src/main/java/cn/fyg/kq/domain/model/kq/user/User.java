package cn.fyg.kq.domain.model.kq.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bs_user")
public class User {
	
	@Id
	@Column(name="fid")
	private String fid;//easid
	
	private String fnumber;//用户名
	
	private String fname;//用户实名
	
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
	
	

}
