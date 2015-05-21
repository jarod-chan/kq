package cn.fyg.kq.infrastructure.tool.date;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class DateUtil {
	
	public static Date today(){
		return new Date();
	}
	
	public static Date nextDay(Date day){
		DateTime dateTime = new DateTime(day);   
		dateTime=dateTime.plusDays(1);
		return dateTime.toDate();   
	}
	
	public static int year(){
		DateTime dateTime = new DateTime(new Date());  
		return dateTime.getYear();
	}
	
	public static int month(){
		DateTime dateTime = new DateTime(new Date());  
		return dateTime.getMonthOfYear()+1;
	}
	
	public static long durationMinutes(Date begDate,Date endDate){
		DateTime startTime = new DateTime(begDate);   
		DateTime endTime = new DateTime(endDate);   
		Duration  duration = new Duration(startTime,endTime);
		return duration.getStandardMinutes();
	}

}
