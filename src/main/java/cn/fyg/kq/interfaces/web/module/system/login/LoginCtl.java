package cn.fyg.kq.interfaces.web.module.system.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.module.user.User;
import cn.fyg.module.user.UserException;
import cn.fyg.module.user.UserService;




@Controller
@RequestMapping("/login")
public class LoginCtl {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	private static final String PATH = "system/login/";
	private interface Page {
		String LOGIN = PATH + "login";
	}
	
	@Autowired
	UserService userService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toLogin() {
		return Page.LOGIN;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(LoginBean loginBean,RedirectAttributes redirectAttributes) {
		String userKey=null;
		try{
			userKey=userService.login(loginBean.getUsername(), loginBean.getPassword());
		}catch(UserException userException){
			logger.error(String.format("key:%s password:%s login fail", loginBean.getUsername(),loginBean.getPassword()));	
		}
		if(userKey==null){
			redirectAttributes.addFlashAttribute("loginBean", loginBean);
			redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, "用户名或者密码错误！");
			return "redirect:/login";
		}
		initLoginState(userKey);
		return "redirect:/frame";
	}

	private void initLoginState(String userKey) {
		User user=userService.findUser(userKey);
		sessionUtil.invalidate();
		sessionUtil.setValue("user", user);
		filterAdmin(user);
	}

	/**
	 * XXX 临时管理员处理
	 * @param user
	 */
	private void filterAdmin(User user) {
		if(user.getKey().equals("admin")){
			sessionUtil.setValue("isAdmin", Boolean.TRUE);
		}else{
			sessionUtil.setValue("isAdmin", Boolean.FALSE);
		}
		sessionUtil.setValue("nofilter", Boolean.TRUE);
	}


}
