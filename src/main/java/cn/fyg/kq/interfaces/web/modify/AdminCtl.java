package cn.fyg.kq.interfaces.web.modify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.application.KQUserService;


@Controller
@RequestMapping("/md")
public class AdminCtl {
	
	private static final String PATH = "modify/";
	private interface Page {
		String ADMIN = PATH + "admin";
	}
	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String toAdmin(@Param("username")String username,Map<String,Object> map){
		if(username!=null&&!username.trim().equals("")){
			List<User> userList=new ArrayList<User>();
			userList.add(new User("fid1","张三","张三","系统用户"));
			userList.add(new User("fid2","李四","李四","职员"));
			userList.add(new User("fid3","王五","李四","职员"));
			map.put("userList", userList);
			map.put("username", username);
		}
		return Page.ADMIN;
	}
	
	@Autowired
	KQUserService kqUserService;
	
	@RequestMapping(value="admin/init",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> init(@Param("fid")String fid,Map<String,Object> map){
		Map<String,Object> ret=new HashMap<String,Object>();
		try{
			this.kqUserService.init(fid);
			ret.put("result", true);
		}catch(Exception e){
			ret.put("result", false);
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

}
