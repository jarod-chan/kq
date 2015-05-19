package cn.fyg.kq.domain.model.checkuser;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum Kqstat implements CommonEnum {
	todo("待定"),yes("是"),no("否");
	
	private final String name;

	private Kqstat(String name) {
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
