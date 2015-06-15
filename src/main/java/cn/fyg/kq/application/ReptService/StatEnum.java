package cn.fyg.kq.application.ReptService;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum StatEnum implements CommonEnum {	
	finish("结束",0,null,Range.falseRange),
	gm_check("总经理审批",1,finish,new Range(){
		@Override
		public boolean inRange(int val) {
			return val>8;
		}
	}),
	vp_check("分管副总审批",2,gm_check,new Range(){
		@Override
		public boolean inRange(int val) {
			return val>4 && val<=8;
		}
	}),
	dm_check("部门经理审批",3,vp_check,new Range(){
		@Override
		public boolean inRange(int val) {
			return val>0 && val<=4;
		}
	}),
	start("开始",4,dm_check,Range.falseRange);
	
	private final String name;
	private final int level;
	private final StatEnum next_node;
	private final Range range;
	
	
	private StatEnum(String name, int level, StatEnum next_node,Range range) {
		this.name = name;
		this.level = level;
		this.next_node = next_node;
		this.range=range;
	}
	


	@Override
	public String getName() {
		return this.name;
	}

	//获得上一级
	public StatEnum next(){
		return this.next_node;
	}
	
	//获得级次
	public int level(){
		return this.level;
	}
	
	//判断是否权限更高
	public boolean higher(StatEnum statEnum){
		return this.level<=statEnum.level;
	}
	
	
	//获得需要的审批级别
	public static StatEnum rangOf(int val){
		for(StatEnum statEnum:StatEnum.values()){
			if(statEnum.range.inRange(val)){
				return statEnum;
			}
		}
		return null;
	}
	
	//通过级别获得审批级次
	public static StatEnum levelOf(int level){
		for(StatEnum statEnum:StatEnum.values()){
			if(statEnum.level==level){
				return statEnum;
			}
		}
		return null;
	}
	

}
