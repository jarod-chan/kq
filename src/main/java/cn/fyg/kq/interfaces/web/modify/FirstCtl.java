package cn.fyg.kq.interfaces.web.modify;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/md")
public class FirstCtl {
	
	private static final String PATH = "modify/";
	private interface Page {
		String FIRST = PATH + "first";
	}
	
	@RequestMapping(value="first",method=RequestMethod.GET)
	public String toFisrst(Map<String,Object> map){
		return Page.FIRST;
	}

}
