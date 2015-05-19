package cn.fyg.kq.domain.model.period;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum PeriodState implements CommonEnum {
	create("新建"),
	docal("计算中"),
	finishcal("计算完成"),
	produce("已生产");
	
	private final String name;
	
	private PeriodState(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
