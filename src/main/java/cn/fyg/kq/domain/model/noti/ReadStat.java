package cn.fyg.kq.domain.model.noti;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum ReadStat  implements CommonEnum {
	y("已读"),n("未读");
	
	private final String name;

	ReadStat(String name){
		this.name=name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
