package cn.fyg.kq.interfaces.web.module.imptsys;

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

import cn.fyg.easser.domain.User;
import cn.fyg.easser.service.EasUserService;
import cn.fyg.easser.service.NetException;

@Controller
@RequestMapping("easser")
public class EasserCtl {
	
	
	@Autowired
	EasUserService easUserService;
	
	@RequestMapping(value="user.json",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryEasuser(@Param("name")String name){
		Map<String,Object> ret = new HashMap<String,Object>();
		
		List<User> userList=new ArrayList<User>();
		boolean isfinish=false;
		String message=null;
		try {
			userList = easUserService.query(name);
			isfinish=true;
		} catch (NetException e) {
			message="连接EAS服务器出错！";
		} catch (Exception e) {
			message="EAS调用过程出错！";
		}
		if(!isfinish){
			ret.put("data", message);
			ret.put("result", false);
			return ret;
		}
		
		if(userList.size()>10){
			userList=userList.subList(0, 10);
		}
		
		ret.put("data", userList);
		ret.put("result", true);
		return ret;
	}

}
