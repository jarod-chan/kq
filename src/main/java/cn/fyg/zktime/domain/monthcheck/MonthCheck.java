package cn.fyg.zktime.domain.monthcheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.CheckinoutMapper;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.domain.UserrunMapper;

/**
 *月考勤记录
 */
public class MonthCheck {
	
	private List<DateCheck> datechecks;

	public List<DateCheck> getDatechecks() {
		return datechecks;
	}

	public void setDatechecks(List<DateCheck> datechecks) {
		this.datechecks = datechecks;
	}
	
	/*
	 * 完成初始化
	 */
	public void finishInit(){
		for(DateCheck dateCheck:this.getDatechecks()){
			dateCheck.compareCheckinout();
		}
	}
	
	
	public void initDate(int userid,int year,int month,UserrunMapper userrunMapper,CheckinoutMapper checkinoutMapper){
		this.datechecks=new ArrayList<DateCheck>();
		//获得一个月的每一天
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
		Property dayOfMonth = dateTime.dayOfMonth();
		int maxdays = dayOfMonth.getMaximumValue();
		for (int i = 1; i < maxdays; i++) {
			DateCheck dateCheck = new DateCheck();
			Date date = new DateTime(year, month, i, 0, 0, 0, 0).toDate();
			dateCheck.setDate(date);
			this.datechecks.add(dateCheck);
		}

		
		for(DateCheck dateCheck:this.datechecks){
			Date currdate = dateCheck.getDate();
			Integer runidofdate = userrunMapper.runidofdate(userid, currdate);//获得当天得班次
			int currday = new DateTime(currdate).dayOfWeek().get();
			List<Schclass> schclasses = new ArrayList<Schclass>();
			if(runidofdate!=null){
				schclasses=userrunMapper.schclassofrun(runidofdate, currday);//获得班次时段，一天可能有多个时段
			}
			dateCheck.setSchclasses(schclasses);
		}
		
		//获得摸个员工一个月所有的打卡时间
		List<Checkinout> monthCheckinout = checkinoutMapper.monthCheckinout(userid, year, month);
		
		
		for(DateCheck dateCheck:this.datechecks){
			List<Checkinout> checkinouts = new ArrayList<Checkinout>();
			checkinouts=getfrom(monthCheckinout,dateCheck.getDate()); 
			dateCheck.setCheckinout(checkinouts);
		}
		
		//对比每天班次时段和打卡记录
		for(DateCheck dateCheck:this.datechecks){
			dateCheck.compareCheckinout();
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
