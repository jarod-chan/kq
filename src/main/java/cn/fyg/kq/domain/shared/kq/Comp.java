package cn.fyg.kq.domain.shared.kq;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum Comp implements CommonEnum {
	jianshen("建设公司"),
	fangchan("房产公司"),
	jituan("集团");
	
	private final String name;

	Comp(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
