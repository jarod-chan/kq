package cn.fyg.kq.interfaces.web.module.system.notfound;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("404")
public class NotfoundCtl {
	
	
	private static final String PATH = "system/notfound/";
	private interface Page {
		String NOTFOUND = PATH + "notfound";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String toNotfound(Map<String,Object> map) {
		return Page.NOTFOUND;
	}

}
