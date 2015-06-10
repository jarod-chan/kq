package cn.fyg.easser.service;

public interface LoginService {

	/**
	 * 用eas用户名和密码登陆
	 * @param username 用户名
	 * @param password 密码
	 * @return 
	 */
	String login(String username, String password) throws NetException, Exception;

}
