package cn.fyg.zktime.domain.monthcheck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.infrastructure.tool.date.DateUtil;
import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.CheckinoutMapper;
import cn.fyg.zktime.domain.Numrun;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.domain.Userrun;
import cn.fyg.zktime.domain.UserrunMapper;
import cn.fyg.zktime.domain.UserrunWrap;

@Component
public class MonthCheckBuilder {
	
	@Autowired
	UserrunMapper userrunMapper;
	
	@Autowired
	CheckinoutMapper checkinoutMapper;
	
	
	/**
	 * 创建一个班次时段的缓存
	 * @return
	 */
	public NumrunCache createNumrunCache(){
		return new NumrunCache(userrunMapper);
	}
	
	public MonthCheck build(Param param,NumrunCache numrunCache){
		MonthCheck monthCheck=new MonthCheck();
		
		initDatecheck_date(monthCheck,param);

		initDatecheck_schclasses(monthCheck,param,numrunCache);
		
		initDatecheck_checkinout(monthCheck,param);
		
		monthCheck.finishInit();
	
		return monthCheck;
	}
	
	/*
	 * 生成当月的所有日期
	 * */
	private void initDatecheck_date(MonthCheck monthCheck,Param param) {
		List<DateCheck> datechecks = new ArrayList<DateCheck>();
		//获得一个月的每一天
		DateTime dateTime = new DateTime(param.getYear(), param.getMonth(), 1, 0, 0, 0, 0);
		Property dayOfMonth = dateTime.dayOfMonth();
		int maxdays = dayOfMonth.getMaximumValue();
		for (int i = 1; i < maxdays; i++) {
			DateCheck dateCheck = new DateCheck();
			Date date = new DateTime(param.getYear(), param.getMonth(), i, 0, 0, 0, 0).toDate();
			dateCheck.setDate(date);
			datechecks.add(dateCheck);
		}
		monthCheck.setDatechecks(datechecks);
	}
	
	/*
	 * 生成当月每天的时段
	 */
	private void initDatecheck_schclasses(MonthCheck monthCheck, Param param,NumrunCache numrunCache) {
		Date monthBeg = DateUtil.monthBeg(param.getYear(),param.getMonth(),59);
		Date monthEnd = DateUtil.monthEnd(param.getYear(),param.getMonth(),59);

		//获得一个月所有关的排班，根据日期筛选班次id
		List<Userrun> userruns = this.userrunMapper.userrun(param.getUserid(), monthBeg, monthEnd);
		UserrunWrap wrap=new UserrunWrap(userruns);
		List<DateCheck> datechecks = monthCheck.getDatechecks(); 						
		for(DateCheck dateCheck:datechecks){
			Date date = dateCheck.getDate();
			Userrun userrun = wrap.findUserrun(date);
			if(thisDateHaveUserrun(userrun)){
				Numrun numrun = numrunCache.get(userrun.getNum_of_run_id());
				if(thisUserrunHaveSchclasses(numrun)){
					int dayOfWeek=DateUtil.dayOfWeek(date);
					List<Schclass> schclasses = numrun.getSchclass(dayOfWeek);
					dateCheck.setSchclassesToInOut(schclasses);
					continue;
				}
			}
			dateCheck.setSchclassInOuts(Collections.<SchclassInOut>emptyList());
		}
		
	}

	//判断该班次是否存在时段
	private boolean thisUserrunHaveSchclasses(Numrun numrun) {
		return numrun!=null;
	}

	//判断当天是否存在排班
	private boolean thisDateHaveUserrun(Userrun userrun) {
		return userrun!=null;
	}

	/*
	 *生成当月每天的打卡记录
	 */
	private void initDatecheck_checkinout(MonthCheck monthCheck,Param param){
		List<Checkinout> monthCheckinout=this.checkinoutMapper.monthCheckinout(param.getUserid(), param.getYear(), param.getMonth());
		
		List<DateCheck> datechecks = monthCheck.getDatechecks(); 						
		for(DateCheck dateCheck:datechecks){
			List<Checkinout> checkinouts = new ArrayList<Checkinout>();
			checkinouts=getfrom(monthCheckinout,dateCheck.getDate()); 
			dateCheck.setCheckinout(checkinouts);
		}
	}


	private List<Checkinout> getfrom(List<Checkinout> monthCheckinout, Date date) {
		List<Checkinout> checkinouts = new ArrayList<Checkinout>();
		for(Checkinout checkinout:monthCheckinout){
			if(DateUtils.isSameDay(checkinout.getChecktime(), date)){
				checkinouts.add(checkinout);
			}
		}
		return checkinouts;
	}

}
