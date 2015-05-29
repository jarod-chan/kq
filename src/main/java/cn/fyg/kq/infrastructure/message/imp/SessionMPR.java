package cn.fyg.kq.infrastructure.message.imp;

import javax.servlet.http.HttpSession;

import cn.fyg.kq.infrastructure.message.MessagePasser;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;

public class SessionMPR implements MessagePasser {
	
	private HttpSession session;
	
	public SessionMPR(HttpSession session){
		this.session=session;
	}

	@Override
	public void setMessage(String message) {
		session.setAttribute(AppConstant.MESSAGE_NAME, message);
	}

	@Override
	public String getMessage() {
		Object obj=session.getAttribute(AppConstant.MESSAGE_NAME);
		session.setAttribute(AppConstant.MESSAGE_NAME, null);
		return obj==null?null:(String) obj;
	}

}
