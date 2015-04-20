package cn.fyg.kq.interfaces.web.modify;

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
import cn.fyg.kq.interfaces.web.modify.http.AdminHelp;
import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.service.UserinfoService;


@Controller
@RequestMapping("/md")
public class AdminCtl {
	
	private static final String PATH = "modify/";
	private interface Page {
		String ADMIN = PATH + "admin";
		String INIT = PATH + "init";
	}
	
	@Autowired
	AdminHelp adminHelp;
	@Autowired
	KQUserService kqUserService;
	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String toAdmin(@Param("username")String username,Map<String,Object> map){
		if(username!=null&&!username.trim().equals("")){
			List<User> userList=adminHelp.query(username);
			if(userList.size()>10){
				userList=userList.subList(0, 10);
			}
			checkInit(userList);
			map.put("userList", userList);
			map.put("username", username);
			
		}
		return Page.ADMIN;
	}
	
	private void checkInit(List<User> userList) {
		for (User user : userList) {
			if(this.kqUserService.isInit(user.getFid())){				
				user.setInit(true);
			}
		}
	}
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public String toInit(@Param("fid")String fid,@Param("fnumber")String fnumber,@Param("fname")String fname,Map<String,Object> map){
		map.put("fid", fid);
		map.put("fnumber",fnumber);
		map.put("fname", fname);
		return Page.INIT;
	}
	
	@Autowired
	UserinfoService userinfoService;
	
	@RequestMapping(value="queryKquser",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryKquser(@Param("name")String name){
		List<Userinfo> userinfoList = this.userinfoService.queryByName(name);
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", userinfoList);
		ret.put("result", true);
		return ret;
	}
	
	

	
	@RequestMapping(value="admin/init",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> init(@Param("fid")String fid,@Param("fnumber")String fnumber,@Param("fname")String fname,@Param("userid")int userid,Map<String,Object> map){
		Map<String,Object> ret=new HashMap<String,Object>();
		try{
			this.kqUserService.init(fid,fnumber,fname,userid);
			ret.put("result", true);
		}catch(Exception e){
			ret.put("result", false);
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

}
