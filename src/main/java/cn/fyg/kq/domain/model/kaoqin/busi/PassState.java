package cn.fyg.kq.domain.model.kaoqin.busi;

import cn.fyg.kq.domain.shared.CommonEnum;


public enum PassState implements CommonEnum {

	accept("认可"),
	no("不认可"); 
	
	private final String name;

	private PassState(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
