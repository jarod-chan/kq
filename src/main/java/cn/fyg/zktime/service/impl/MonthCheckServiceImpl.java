package cn.fyg.zktime.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.zktime.domain.monthcheck.MonthCheck;
import cn.fyg.zktime.domain.monthcheck.MonthCheckBuilder;
import cn.fyg.zktime.domain.monthcheck.NumrunCache;
import cn.fyg.zktime.domain.monthcheck.Param;
import cn.fyg.zktime.service.MonthCheckService;

@Service
public class MonthCheckServiceImpl implements  MonthCheckService{
	
	@Autowired
	MonthCheckBuilder builder;
	
	
	@Override
	@Transactional("ts_zktime")
	public MonthCheck getMonthCheck(int userid,int year,int month){
		Param param = new Param(userid,year,month);
		NumrunCache numrunCache = builder.createNumrunCache();
		return builder.build(param,numrunCache);
	}

	
	@Override
	@Transactional("ts_zktime")
	public List<MonthCheck> getMonthCheck(List<Integer> userids, int year,
			int month) {
		List<MonthCheck> monthChecks = new ArrayList<MonthCheck>();
		NumrunCache numrunCache = builder.createNumrunCache();
		for(Integer userid:userids){
			Param param = new Param(userid,year,month);
			MonthCheck monthCheck = builder.build(param,numrunCache);
			monthChecks.add(monthCheck);
		}
		return monthChecks;
	}
	

}
