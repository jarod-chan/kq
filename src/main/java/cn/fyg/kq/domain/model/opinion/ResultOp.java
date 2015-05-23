package cn.fyg.kq.domain.model.opinion;

import java.util.ArrayList;
import java.util.List;

import cn.fyg.kq.domain.shared.CommonEnum;

public enum ResultOp implements CommonEnum {
	agree("同意",Boolean.TRUE),
	disagree("不同意",Boolean.FALSE),
	
	pass("通过",Boolean.TRUE),
	nopass("不通过",Boolean.FALSE),
	
	checked("确认","");
	
	private String name;
	
	private Object val;//流程值，用来控制流程分支
	
	private<T> ResultOp(String name,T val){
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
	
	public static List<ResultOp> agreeItems(){
		List<ResultOp> items=new ArrayList<ResultOp>();
		items.add(ResultOp.agree);
		items.add(ResultOp.disagree);
		return items;
	}
	
	public static List<ResultOp> passItems(){
		List<ResultOp> items=new ArrayList<ResultOp>();
		items.add(ResultOp.pass);
		items.add(ResultOp.nopass);
		return items;
	}
	
}
