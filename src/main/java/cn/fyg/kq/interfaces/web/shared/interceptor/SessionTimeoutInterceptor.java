package cn.fyg.kq.interfaces.web.shared.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

/**
 * 处理session超时的拦截器
 */
@Component
public class SessionTimeoutInterceptor  implements HandlerInterceptor{
	
	@Autowired
	SessionUtil sessionUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = sessionUtil.getValue("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/404");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}