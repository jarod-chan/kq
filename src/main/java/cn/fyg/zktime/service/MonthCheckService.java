package cn.fyg.zktime.service;

import java.util.List;

import cn.fyg.zktime.domain.monthcheck.MonthCheck;

public interface MonthCheckService {

	MonthCheck getMonthCheck(int userid, int year, int month);

	List<MonthCheck> getMonthCheck(List<Integer> userids,int year,int month);
}
