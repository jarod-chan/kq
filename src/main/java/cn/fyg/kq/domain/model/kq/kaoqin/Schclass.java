package cn.fyg.kq.domain.model.kq.kaoqin;

import javax.persistence.Embeddable;

/**
 *考勤时段
 */
@Embeddable
public class Schclass {
	
	private String name;//	时段	文本副本
	
	private SchclassInout inout;//	签到/签退	 in out
	
	private String begendtime;//	开始结束时间	文本副本


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SchclassInout getInout() {
		return inout;
	}

	public void setInout(SchclassInout inout) {
		this.inout = inout;
	}

	public String getBegendtime() {
		return begendtime;
	}

	public void setBegendtime(String begendtime) {
		this.begendtime = begendtime;
	}
	
	

}
