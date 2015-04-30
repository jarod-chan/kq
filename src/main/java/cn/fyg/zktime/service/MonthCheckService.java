package cn.fyg.zktime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.zktime.domain.CheckinoutMapper;
import cn.fyg.zktime.domain.MonthCheck;
import cn.fyg.zktime.domain.UserrunMapper;

@Service
public class MonthCheckService {
	
	@Autowired
	UserrunMapper userrunMapper;
	
	@Autowired
	CheckinoutMapper checkinoutMapper;
	
	public MonthCheck getMonthCheck(int year,int month,int userid){
		MonthCheck monthCheck = new MonthCheck();
		monthCheck.initDate(userid, year, month, userrunMapper, checkinoutMapper);	
		return monthCheck;
	}

}
