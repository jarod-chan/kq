package cn.fyg.kq.domain.model.kq.admincomp;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum IskqEnum implements CommonEnum {
	yes("是"),no("否");
	
	private final String name;

	private IskqEnum(String name) {
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
