package cn.fyg.kq.interfaces.web.shared.session.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.module.user.User;
import cn.fyg.module.user.UserService;

/**
 * session 处理
 */
@Component
public class SessionUtilImpl implements SessionUtil {
	
	@Autowired
	HttpSession httpSession;
	@Autowired
	UserService userService;
	
	@Override
	public void setValue(String key,Object value){
		httpSession.setAttribute(key, value);
	}
	
	
	@Override
	public <T> T getValue(String key){
		Object obj=httpSession.getAttribute(key);
		if(obj==null) return null;
		@SuppressWarnings("unchecked")
		T returnVal = (T)obj;
		return returnVal;
	}
	
	@Override
	public void invalidate(){
		httpSession.invalidate();
	}


	@Override
	public User getUser() {
		User user=getValue("user");
		if(user==null){
			user=userService.findUser("chenzw");
		}
		return user;
	}
	
}
