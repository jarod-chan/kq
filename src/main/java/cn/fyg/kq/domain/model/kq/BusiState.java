package cn.fyg.kq.domain.model.kq;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum BusiState implements CommonEnum {
	new_("新建"),
	execute("执行"),
	finish("完成"),
	voided("作废"); 
	
	private String name;

	private BusiState(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
