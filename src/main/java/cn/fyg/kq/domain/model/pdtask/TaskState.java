package cn.fyg.kq.domain.model.pdtask;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum TaskState implements CommonEnum {
	append("添加"),
	start("开始"),
	end("结束");
	
	private final String name;
	
	private TaskState(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
