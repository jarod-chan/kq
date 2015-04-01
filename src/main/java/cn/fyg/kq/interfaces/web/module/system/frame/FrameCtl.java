package cn.fyg.kq.interfaces.web.module.system.frame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frame")
public class FrameCtl {
	
	private static final String PATH = "system/frame/";
	private interface Page {
		String FRAME = PATH + "frame";
		String SUB=PATH + "sub";
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toQui(){
		return Page.FRAME;
	}
	
	@RequestMapping(value="sub",method=RequestMethod.GET)
	public String toSub(){
		return Page.SUB;
	}

}
