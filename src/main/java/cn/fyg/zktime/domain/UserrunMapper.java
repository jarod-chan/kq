package cn.fyg.zktime.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserrunMapper {
	
	Integer runidofdate(@Param("userid")int userid,@Param("monthdate")Date date);
	
	List<Schclass> schclassofrun(@Param("num_runid")int num_runid,@Param("day")int day);
	
	List<Userrun> userrun(@Param("userid")int userid,@Param("monthbeg")Date monthbeg,@Param("monthend")Date monthend);
	
	Numrun findNumrun(@Param("num_runid")int num_runid);

}
