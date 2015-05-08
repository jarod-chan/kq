package cn.fyg.kq.domain.model.kq.period;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.fyg.kq.domain.model.kq.kaoqin.MonthItem;

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
	
	private String comp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MonthItem getMonthitem() {
		return monthitem;
	}

	public void setMonthitem(MonthItem monthitem) {
		this.monthitem = monthitem;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}
	
	

}
