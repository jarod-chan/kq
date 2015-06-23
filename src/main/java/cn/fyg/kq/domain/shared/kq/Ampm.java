package cn.fyg.kq.domain.shared.kq;

import java.util.Date;

import cn.fyg.kq.domain.shared.CommonEnum;
import cn.fyg.kq.infrastructure.tool.date.DateUtil;

public enum Ampm implements CommonEnum {
	
	am("上午",1,DateUtil.dateZeroAmBeg(),DateUtil.dateZeroAmEnd()),
	pm("下午",2,DateUtil.dateZeroPmBeg(),DateUtil.dateZeroPmEnd());
	
	private String name;
	private int value;
	private final Date beg;
	private final Date end;

	private Ampm(String name,int value,Date beg,Date end) {
		this.name = name;
		this.value=value;
		this.beg=beg;
		this.end=end;
	}
	
	public int value(){
		return this.value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean timeInAmpm(Date date){
		Date zeroDate=DateUtil.dateDateZero(date);
		return DateUtil.inDate(zeroDate, this.beg, this.end);
	}
	
}
