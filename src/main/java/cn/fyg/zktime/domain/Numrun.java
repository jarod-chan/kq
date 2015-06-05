package cn.fyg.zktime.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *班次
 */
public class Numrun {
	
	private int numrunid;
	
	private List<NumrunDeil> numrunDeils=new ArrayList<NumrunDeil>();

	public int getNumrunid() {
		return numrunid;
	}

	public void setNumrunid(int numrunid) {
		this.numrunid = numrunid;
	}

	public List<NumrunDeil> getNumrunDeils() {
		return numrunDeils;
	}

	public void setNumrunDeils(List<NumrunDeil> numrunDeils) {
		this.numrunDeils = numrunDeils;
	}

	//获得班次在每周的固定时间的班次 sdays,edays条件根据zktime系统推测，不是很确定的条件
	public List<Schclass> getSchclass(int dayOfWeek){
		List<Schclass> schclasses = new ArrayList<Schclass>();
		for(NumrunDeil deil:numrunDeils){
			if(deil.getSdays()==dayOfWeek||deil.getEdays()==dayOfWeek){
				schclasses.add(deil.getSchclass());
			}
		}
		return schclasses;
	}
	
}
