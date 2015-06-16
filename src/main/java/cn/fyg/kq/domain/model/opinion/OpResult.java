package cn.fyg.kq.domain.model.opinion;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum OpResult implements CommonEnum {
	agree("同意",Boolean.TRUE),
	disagree("不同意",Boolean.FALSE),
	
	pass("通过",Boolean.TRUE),
	nopass("不通过",Boolean.FALSE),
	
	checked("确认","checked");
	
	private String name;
	
	private Object val;//流程值，用来控制流程分支
	
	private<T> OpResult(String name,T val){
		this.name=name;
		this.val=val;
	}
	
	@Override
	public String getName() {
		return this.name;
	}


	@SuppressWarnings("unchecked")
	public<T> T val() {
		return (T) val;
	}
	
	public static List<OpResult> agreeItems(){
		List<OpResult> items=new ArrayList<OpResult>();
		items.add(OpResult.agree);
		items.add(OpResult.disagree);
		return items;
	}
	
	public static List<OpResult> passItems(){
		List<OpResult> items=new ArrayList<OpResult>();
		items.add(OpResult.pass);
		items.add(OpResult.nopass);
		return items;
	}
	
	public static List<OpResult> checkItems(){
		List<OpResult> items=new ArrayList<OpResult>();
		items.add(OpResult.checked);
		return items;
	}
	
}
