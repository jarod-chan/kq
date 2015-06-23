package cn.fyg.kq.domain.model.exclude;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.fyg.kq.domain.shared.kq.Dayitem;

/**
 *排除的日期
 *以半天为单位
 */
@Entity
@Table(name="kq_exclude")
public class Exclude {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; // 主键	
	
	private Long period_id;
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "date", column = @Column(name = "day_date")),
			@AttributeOverride(name = "ampm", column = @Column(name = "day_ampm")) })
	private Dayitem dayitem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(Long period_id) {
		this.period_id = period_id;
	}

	public Dayitem getDayitem() {
		return dayitem;
	}

	public void setDayitem(Dayitem dayitem) {
		this.dayitem = dayitem;
	}

	
}
