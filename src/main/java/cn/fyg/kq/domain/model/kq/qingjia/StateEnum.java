package cn.fyg.kq.domain.model.kq.qingjia;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum StateEnum implements CommonEnum {
	save("暂存"),
	commit("已提交"),
	process("审批中"), 
	finish("完成"),
	voided("作废"); 
	
	private final String name;

	private StateEnum(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
