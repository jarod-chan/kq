package cn.fyg.zktime.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.zktime.domain.CheckinoutMapper;
import cn.fyg.zktime.domain.UserrunMapper;
import cn.fyg.zktime.domain.monthcheck.MonthCheck;
import cn.fyg.zktime.service.MonthCheckService;

@Service
public class MonthCheckServiceImpl implements  MonthCheckService{
	
	@Autowired
	UserrunMapper userrunMapper;
	
	@Autowired
	CheckinoutMapper checkinoutMapper;
	
	@Override
	public MonthCheck getMonthCheck(int userid,int year,int month){
		MonthCheck monthCheck = new MonthCheck();
		monthCheck.initDate(userid, year, month, userrunMapper, checkinoutMapper);	
		return monthCheck;
	}

}
