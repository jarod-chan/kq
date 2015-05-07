package cn.fyg.kq.domain.model.kq.kaoqin;

import cn.fyg.kq.domain.shared.CommonEnum;


public enum PassState implements CommonEnum {

	pass("通过"),
	no("未通过"); 
	
	private final String name;

	private PassState(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
