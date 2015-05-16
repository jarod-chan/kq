package cn.fyg.kq.interfaces.web.module.adminsys.user;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserCtl {
	
	private static final String PATH = "kq/admin/";
	private interface Page {
		String LIST = PATH + "list";
	}
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		
		return Page.LIST;
	}

}
