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
	
	public static int dayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.dayOfWeek().get();
	}
	
	public static Date monthBeg(int year,int month,int second){
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, second, 0);
		return dateTime.toDate();
	}
	
	public static Date monthEnd(int year,int month,int second){
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, second, 0);
		dateTime=dateTime.plusMonths(1).minusDays(1);
		return dateTime.toDate();
	}
	
	/**
	 * 日期调整成1900-1-1日，时间不变
	 * @param date
	 * @return
	 */
	public static Date dateDateZero(Date date){
		DateTime dateTime = new DateTime(date);
		return dateTime.withDate(1900, 1, 1).toDate();
	}
	
	/**
	 * 时间调整成0时0分0秒，日期不变
	 * @param date
	 * @return
	 */
	public static Date dateTimeZero(Date date){
		DateTime dateTime = new DateTime(date);
		return dateTime.withTime(0, 0, 0, 0).toDate();
	}
	
	public static boolean inDate(Date date,Date begin,Date end){
		return date.compareTo(begin)>=0 &&
				date.compareTo(end)<=0;
	}
	
	//TODO 待修改
	public static String minute(int minutes){
		DateTime dateTime = new DateTime(new Date()); 
		dateTime=dateTime.plusMonths(1).plusMinutes(minutes);
		Date date = dateTime.toDate();
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}
	
	public static void main(String[] args) {
//		System.out.println(minute(2));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(monthBeg(2015,5,59)));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(monthEnd(2015,5,59)));
		p(dateTimeZero(monthEnd(2015,5,59)));
	}
	
	private static void  p(Date date){
		System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date) );
	}



}
