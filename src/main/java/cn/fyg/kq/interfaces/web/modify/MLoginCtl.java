package cn.fyg.kq.interfaces.web.modify;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.interfaces.web.modify.http.LoginHelp;


@Controller
@RequestMapping("/md")
public class MLoginCtl {
	
	private static final String PATH = "modify/";
	private interface Page {
		String LOGIN = PATH + "login";
		String RESULT = PATH + "result";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String toLogin(Map<String,Object> map){
		return Page.LOGIN;
	}
	
	@Autowired
	LoginHelp loginHelp;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(LoginBean loginBean,Map<String,Object> map){
		loginProcess(loginBean,map);
		return Page.RESULT;
	}

	private void loginProcess(LoginBean loginBean, Map<String, Object> map) {
		map.put("result", false);
		ArrayList<String> detail = new ArrayList<String>();
		map.put("detail", detail);
		String fid=this.loginHelp.loginEAS(loginBean.getUsername(), loginBean.getPassword());
		if(fid==null){
			detail.add("eas校验失败");
			return;
		}else{
			detail.add("eas校验通过");
		}
		boolean kqInit = this.loginHelp.isKQInit(fid);
		if(!kqInit){
			detail.add("考勤系统未初始化");
			return;
		}else{
			detail.add("考勤系统已经初始化");
		}
		map.put("result", true);
		detail.add("登录完成");
	}
	

}
