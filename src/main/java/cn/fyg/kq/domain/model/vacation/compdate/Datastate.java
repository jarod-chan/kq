package cn.fyg.kq.domain.model.vacation.compdate;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum Datastate implements CommonEnum {
	none ("不存在"),
	exist ("存在");

	
	private String name;

	private Datastate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
