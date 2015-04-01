package cn.fyg.kq.domain.model.opinion;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.kq.domain.model.shared.CommonEnum;

public enum Result implements CommonEnum {
	agree("同意",Boolean.TRUE),
	disagree("不同意",Boolean.FALSE),
	
	pass("通过",Boolean.TRUE),
	nopass("不通过",Boolean.FALSE),
	
	checked("确认","");
	
	private String name;
	
	private Object val;//流程值，用来控制流程分支
	
	private<T> Result(String name,T val){
		this.name=name;
		this.val=val;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@SuppressWarnings("unchecked")
	public<T> T val() {
		return (T) val;
	}
	
	public static List<Result> agreeItems(){
		List<Result> items=new ArrayList<Result>();
		items.add(Result.agree);
		items.add(Result.disagree);
		return items;
	}
	
	public static List<Result> passItems(){
		List<Result> items=new ArrayList<Result>();
		items.add(Result.pass);
		items.add(Result.nopass);
		return items;
	}
	
}
