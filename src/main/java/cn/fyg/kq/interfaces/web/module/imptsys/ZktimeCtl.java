package cn.fyg.kq.interfaces.web.module.imptsys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.service.UserinfoService;


@Controller
@RequestMapping("zktime")
public class ZktimeCtl {
	
	
	@Autowired
	UserinfoService userinfoService;
	
	@RequestMapping(value="user.json",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryKquser(@Param("name")String name){
		List<Userinfo> userinfoList = this.userinfoService.queryByName(name);
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", userinfoList);
		ret.put("result", true);
		return ret;
	}


}
