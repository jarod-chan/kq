package cn.fyg.kq.domain.model.noti;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fyg.kq.domain.model.user.User;

@Entity
@Table(name="kq_noti")
public class Noti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键	
	
	@Column(length=1024)
	private String noti;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "fid")
	private User receiver;// 考勤人员
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;// 创建时间
	
	@Enumerated(EnumType.STRING)
	private ReadStat stat;//已读状态
	
	@PrePersist
	private void prePersist(){
		this.createtime=new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoti() {
		return noti;
	}

	public void setNoti(String noti) {
		this.noti = noti;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public ReadStat getStat() {
		return stat;
	}

	public void setStat(ReadStat stat) {
		this.stat = stat;
	}	
	
	public String getSnoti() {
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(this.noti);
		return m_html.replaceAll(""); // 过滤html标签
	}

}
