package cn.fyg.kq.domain.model.qingjia;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.fyg.kq.domain.shared.kq.Dayitem;


@Entity
@Table(name="kq_qingjiaitem")
public class QingjiaItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// id

	private Long sn;// 序号

	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="date", column=@Column(name="day_date")),
	    @AttributeOverride(name="ampm", column=@Column(name="day_ampm"))
	})
	private Dayitem dayitem;//开始日期	
	
	@Enumerated(EnumType.STRING)
	private IsRealEnum isreal;//是否参与考勤
		
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public Dayitem getDayitem() {
		return dayitem;
	}

	public void setDayitem(Dayitem dayitem) {
		this.dayitem = dayitem;
	}

	public IsRealEnum getIsreal() {
		return isreal;
	}

	public void setIsreal(IsRealEnum isreal) {
		this.isreal = isreal;
	}
	
}
