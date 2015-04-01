package cn.fyg.kq.domain.model.shared;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import cn.fyg.module.user.User;

/**
 *计算下个单据编号
 */
public class NoComputer {
	
	private static final String SEPARATE = "-";
	
	public static String computeNo(String businessCode,User user,String maxNo){
		String prev=businessCode+(Calendar.getInstance().get(Calendar.YEAR)+"").substring(2);
		String midl=user.getKey().toUpperCase();
		String post=getNextFlowNo(maxNo);
		return prev+SEPARATE+midl+SEPARATE+post;
	}


	private static String getNextFlowNo(String maxNo) {
		if(StringUtils.isBlank(maxNo)){
			return "1";
		}
		maxNo=maxNo.substring(maxNo.lastIndexOf(SEPARATE)+1);
		return String.valueOf(Integer.valueOf(maxNo).intValue()+1);
	}

}
