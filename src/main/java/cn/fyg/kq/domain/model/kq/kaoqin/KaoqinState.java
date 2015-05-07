package cn.fyg.kq.domain.model.kq.kaoqin;

import cn.fyg.kq.domain.shared.CommonEnum;



public enum KaoqinState implements CommonEnum {
	
	produced("已生产"),
	save("暂存"),
	commit("已提交"),
	process("审批中"), 
	finish("完成"),
	voided("作废"); 
	
	private final String name;

	private KaoqinState(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}


}
