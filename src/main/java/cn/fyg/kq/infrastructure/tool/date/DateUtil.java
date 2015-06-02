package cn.fyg.kq.infrastructure.tool.date;

import java.text.SimpleDateFormat;
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
	
	public static String minute(int minutes){
		DateTime dateTime = new DateTime(new Date()); 
		dateTime=dateTime.plusMinutes(minutes);
		Date date = dateTime.toDate();
		return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(minute(2));
	}

}
