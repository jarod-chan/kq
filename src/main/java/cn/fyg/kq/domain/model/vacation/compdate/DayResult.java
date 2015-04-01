package cn.fyg.kq.domain.model.vacation.compdate;

import java.math.BigDecimal;

/**
 *用于返回请假的自然天数、实际天数
 */
public class DayResult {
	
	private BigDecimal natureDay;//自然天数
	
	private BigDecimal acturlDay;//实际天数

	public BigDecimal natureDay() {
		return natureDay;
	}

	public DayResult natureDay(BigDecimal natureDay) {
		this.natureDay = natureDay;
		return this;
	}

	public BigDecimal acturlDay() {
		return acturlDay;
	}

	public DayResult acturlDay(BigDecimal acturlDay) {
		this.acturlDay = acturlDay;
		return this;
	}
	
	
	
}
