package cn.fyg.kq.domain.model.vacation.common;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum AMPM implements CommonEnum {
	
	am("上午",1),
	pm("下午",2);
	
	private String name;
	private int value;

	private AMPM(String name,int value) {
		this.name = name;
		this.value=value;
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
	
	
}
