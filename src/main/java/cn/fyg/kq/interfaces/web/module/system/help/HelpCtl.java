package cn.fyg.kq.interfaces.web.module.system.help;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("help")
public class HelpCtl {
	
	private static final String PATH = "system/help/";
	private interface Page {
		String FORGETPWD = PATH + "forgetpwd";
	}
	

	@RequestMapping(value = "forgetpwd", method = RequestMethod.GET)
	public String toUpdate(Map<String,Object> map) {
		return Page.FORGETPWD;
	}

}
