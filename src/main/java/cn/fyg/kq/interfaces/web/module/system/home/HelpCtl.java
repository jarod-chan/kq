package cn.fyg.kq.interfaces.web.module.system.home;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("home")
public class HelpCtl {
	
	private static final String PATH = "system/home/";
	private interface Page {
		String HOME = PATH + "home";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String toTuser(Map<String,Object> map) {
		return Page.HOME;
	}
	


}
