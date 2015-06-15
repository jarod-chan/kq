package cn.fyg.kq.domain.model.kaoqin.busi;

import cn.fyg.kq.domain.shared.CommonEnum;



public enum KaoqinState implements CommonEnum {
	
	produce("已生成"),
	save("暂存"),
	process("审批中"), 
	finish("完成"),
	overdue("过期"),
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
