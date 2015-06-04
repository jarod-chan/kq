package cn.fyg.zktime.service;

import cn.fyg.zktime.domain.monthcheck.MonthCheck;

public interface MonthCheckService {

	MonthCheck getMonthCheck(int userid, int year, int month);

}
