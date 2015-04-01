package cn.fyg.kq.interfaces.web.module.system.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/first")
public class FirstCtl {
	
	private static final String PATH = "system/first/";
	private interface Page {
		String FIRST = PATH + "first";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toFirst() {
		return Page.FIRST;
	}

}
