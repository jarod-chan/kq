package cn.fyg.kq.domain.model.vacation.common;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum LeaveType implements CommonEnum {
	
	personal("事假"),
	sick("病假"),
	marriage("婚假"),
	maternity("产假"),
	annual("年休假");
	
	private String name;
	
	private LeaveType(String name){
		this.name=name;
	}
	

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}
}
