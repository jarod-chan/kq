package cn.fyg.zktime.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface UserinfoMapper {
	
	public List<Userinfo> all();

	public List<Userinfo> findByName(@Param("qname")String name);

}
