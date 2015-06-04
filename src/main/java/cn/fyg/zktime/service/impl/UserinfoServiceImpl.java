package cn.fyg.zktime.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.domain.UserinfoMapper;
import cn.fyg.zktime.service.UserinfoService;

@Service
public class UserinfoServiceImpl implements UserinfoService {
	
	@Autowired
	UserinfoMapper userinfoMapper;
	
	@Override
	public List<Userinfo> queryByName(String name){
		return this.userinfoMapper.findByName(name);
		
	}

}
