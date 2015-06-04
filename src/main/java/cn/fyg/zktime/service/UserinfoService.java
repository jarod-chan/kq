package cn.fyg.zktime.service;

import java.util.List;

import cn.fyg.zktime.domain.Userinfo;

public interface UserinfoService {

	List<Userinfo> queryByName(String name);

}
