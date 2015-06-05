package cn.fyg.zktime.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.fyg.kq.infrastructure.tool.date.DateUtil;

/**
 *处理班次的计算
 */
public class UserrunWrap {
	
	private List<Userrun> userruns=new ArrayList<Userrun>();

	public UserrunWrap(List<Userrun> userruns) {
		super();
		this.userruns = userruns;
	}
	
	/*
	 * 返回摸个日期对应的用户班次，主要返回班次id
	 */
	public Userrun findUserrun(Date date){
		for (Userrun userrun : this.userruns) {
			//zktime 数据库里面的日期在整点之后有59秒，这里处理误差
			Date begin=DateUtil.dateTimeZero(userrun.getStartdate());
			Date end=DateUtil.dateTimeZero(userrun.getEnddate());
			boolean isDateIn=DateUtil.inDate(date, begin, end);
			if(isDateIn){
				return userrun;
			}
		}
		return null;
	}

	

}
