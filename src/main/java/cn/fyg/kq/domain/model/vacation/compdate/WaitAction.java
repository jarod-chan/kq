package cn.fyg.kq.domain.model.vacation.compdate;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum WaitAction implements CommonEnum {
	normal ("正常"),
	wait_add ("待增加"),
	wait_remove ("待删除");
	
	private String name;

	private WaitAction(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
