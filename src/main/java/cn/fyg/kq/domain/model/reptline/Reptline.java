package cn.fyg.kq.domain.model.reptline;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Comp;

/**
 *定义流程的汇报关系
 */
@Entity
@Table(name="kq_reptline")
public class Reptline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String code;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "fid")
	private User user;// 考勤人员
	
	@Enumerated(EnumType.STRING)
	private Comp comp;//用户管理的公司
	
	private int level;//等级
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comp getComp() {
		return comp;
	}

	public void setComp(Comp comp) {
		this.comp = comp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
