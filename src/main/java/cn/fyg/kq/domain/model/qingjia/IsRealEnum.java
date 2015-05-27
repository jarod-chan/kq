package cn.fyg.kq.domain.model.qingjia;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum IsRealEnum implements CommonEnum {
	yes("是"),
	no("否");
	
	private final String name;

	private IsRealEnum(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
