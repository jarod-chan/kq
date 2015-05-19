package cn.fyg.kq.domain.model.kaoqin;

import cn.fyg.kq.domain.shared.CommonEnum;

/**
 *考勤签到或者签退
 */
public enum SchclassInout implements CommonEnum {

	in("签到"),
	out("签退"); 
	
	private final String name;

	private SchclassInout(String name){
		this.name=name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
