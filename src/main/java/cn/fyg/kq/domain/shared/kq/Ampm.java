package cn.fyg.kq.domain.shared.kq;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum Ampm implements CommonEnum {
	
	am("上午",1),
	pm("下午",2);
	
	private String name;
	private int value;

	private Ampm(String name,int value) {
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
