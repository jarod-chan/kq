package cn.fyg.zktime.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CheckinoutMapper {

	public List<Checkinout> monthCheckinout(@Param("userid")int userid,@Param("year")int year,@Param("month")int month);

}
