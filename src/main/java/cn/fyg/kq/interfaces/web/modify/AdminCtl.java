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


@Controller
@RequestMapping("/md")
public class AdminCtl {
	
	private static final String PATH = "modify/";
	private interface Page {
		String ADMIN = PATH + "admin";
	}
	
	@Autowired
	AdminHelp adminHelp;
	
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String toAdmin(@Param("username")String username,Map<String,Object> map){
		if(username!=null&&!username.trim().equals("")){
			List<User> userList=adminHelp.query(username);
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

	@Autowired
	KQUserService kqUserService;
	
	@RequestMapping(value="admin/init",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> init(@Param("fid")String fid,@Param("fnumber")String fnumber,@Param("fname")String fname,Map<String,Object> map){
		Map<String,Object> ret=new HashMap<String,Object>();
		try{
			this.kqUserService.init(fid,fnumber,fname);
			ret.put("result", true);
		}catch(Exception e){
			ret.put("result", false);
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

}
