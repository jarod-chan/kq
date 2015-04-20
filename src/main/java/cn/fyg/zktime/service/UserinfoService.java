package cn.fyg.zktime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.domain.UserinfoMapper;

@Service
public class UserinfoService {
	
	@Autowired
	UserinfoMapper userinfoMapper;
	
	public List<Userinfo> queryByName(String name){
		return this.userinfoMapper.findByName(name);
		
	}

}
