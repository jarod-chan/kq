package cn.fyg.kq.domain.model.kq.kaoqin;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="kq_kaoqinitem")
public class KaoqinItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// id
	
	private Long sn;// 序号
	
	@Column(name="kq_date")
	@Temporal(TemporalType.DATE)
	private Date date;//	日期	
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "sch_name")),
			@AttributeOverride(name = "inout", column = @Column(name = "sch_inout")),
			@AttributeOverride(name = "begendtime", column = @Column(name = "sch_begendtime")) })
	private Schclass schclass;// 开始日期
		
	private String realtime;//实际打卡时间
	
	private String reason;//事由			
	
	private PassState state;//	状态			通过、不通过

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Schclass getSchclass() {
		return schclass;
	}

	public void setSchclass(Schclass schclass) {
		this.schclass = schclass;
	}

	public String getRealtime() {
		return realtime;
	}

	public void setRealtime(String realtime) {
		this.realtime = realtime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public PassState getState() {
		return state;
	}

	public void setState(PassState state) {
		this.state = state;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	
}
