package cn.fyg.kq.infrastructure.tool.date;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class DateUtil {

	public static Date today() {
		return new Date();
	}

	public static Date nextDay(Date day) {
		DateTime dateTime = new DateTime(day);
		dateTime = dateTime.plusDays(1);
		return dateTime.toDate();
	}

	public static int year() {
		DateTime dateTime = new DateTime(new Date());
		return dateTime.getYear();
	}

	public static int month() {
		DateTime dateTime = new DateTime(new Date());
		return dateTime.getMonthOfYear() + 1;
	}

	public static long durationMinutes(Date begDate, Date endDate) {
		DateTime startTime = new DateTime(begDate);
		DateTime endTime = new DateTime(endDate);
		Duration duration = new Duration(startTime, endTime);
		return duration.getStandardMinutes();
	}

	public static int dayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.dayOfWeek().get();
	}

	public static Date monthBeg(int year, int month, int second) {
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, second, 0);
		return dateTime.toDate();
	}

	public static Date monthEnd(int year, int month, int second) {
		DateTime dateTime = new DateTime(year, month, 1, 0, 0, second, 0);
		dateTime = dateTime.plusMonths(1).minusDays(1);
		return dateTime.toDate();
	}

	/**
	 * 日期调整成1900-1-1日，时间不变
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateDateZero(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.withDate(1900, 1, 1).toDate();
	}

	/**
	 * 时间调整成0时0分0秒，日期不变
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateTimeZero(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.withTime(0, 0, 0, 0).toDate();
	}

	public static Date dateZeroAmBeg() {
		DateTime dateTime = new DateTime(1900, 1, 1, 0, 0);
		return dateTime.toDate();
	}

	public static Date dateZeroAmEnd() {
		DateTime dateTime = new DateTime(1900, 1, 1, 12, 0);
		return dateTime.toDate();
	}

	public static Date dateZeroPmBeg() {
		DateTime dateTime = new DateTime(1900, 1, 1, 12, 0);
		return dateTime.toDate();
	}

	public static Date dateZeroPmEnd() {
		DateTime dateTime = new DateTime(1900, 1, 1, 23, 59);
		return dateTime.toDate();
	}

	/*
	 * 一个日期是否在一段时间之内
	 */
	public static boolean inDate(Date date, Date begin, Date end) {
		return date.compareTo(begin) >= 0 && date.compareTo(end) <= 0;
	}

	public static Date minutes(int minutes) {
		DateTime dateTime = new DateTime(new Date());
		dateTime = dateTime.plusMinutes(minutes);
		return dateTime.toDate();
	}
	
	public static Date days(int days){
		DateTime dateTime = new DateTime(new Date());
		dateTime = dateTime.plusDays(days);
		return dateTime.toDate();
	}


}
