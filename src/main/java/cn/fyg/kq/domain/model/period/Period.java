package cn.fyg.kq.domain.model.period;

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

import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.shared.kq.Comp;

/**
 *考勤期间
 */
@Entity
@Table(name="kq_period")
public class Period {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键	
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "year", column = @Column(name = "kq_year")),
			@AttributeOverride(name = "month", column = @Column(name = "kq_month")) })
	MonthItem monthitem;
	
	@Enumerated(EnumType.STRING)
	PeriodState state;//状态
	
	@Enumerated(EnumType.STRING)
	private Comp comp;//	公司主键

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PeriodState getState() {
		return state;
	}

	public void setState(PeriodState state) {
		this.state = state;
	}

	public MonthItem getMonthitem() {
		return monthitem;
	}

	public void setMonthitem(MonthItem monthitem) {
		this.monthitem = monthitem;
	}

	public Comp getComp() {
		return comp;
	}

	public void setComp(Comp comp) {
		this.comp = comp;
	}

	
	

}
