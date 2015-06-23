package cn.fyg.kq.domain.shared.kq;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Dayitem {
	
	public static final BigDecimal ITEM_VALUE=new BigDecimal("0.5");
	
	@Column(name="date_")
	@Temporal(TemporalType.DATE)
	private Date date;//日期
	
	@Enumerated(EnumType.STRING)
	private Ampm ampm;//上下午

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Ampm getAmpm() {
		return ampm;
	}

	public void setAmpm(Ampm ampm) {
		this.ampm = ampm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ampm == null) ? 0 : ampm.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dayitem)) {
			return false;
		}
		Dayitem other = (Dayitem) obj;
		if (ampm != other.ampm) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		return true;
	}
	
	/*
	 * 检查日期是否与当前时间有交集
	 * beg 只检查时间，日期自动转换为1900-1-1
	 * end 只检查时间，日期自动转换为1900-1-1
	 */
	public boolean containDate(Date date,Date beg,Date end){
		if(date==null) return false;
		return this.date.equals(date)
				&& this.ampm.timeInAmpm(beg)
				&& this.ampm.timeInAmpm(end);
	}
	

}
