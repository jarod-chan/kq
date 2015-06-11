package cn.fyg.easser.service;

import java.util.List;

import cn.fyg.easser.domain.User;

public interface EasUserService {

	/**
	 * 查询用户列表
	 * @param username 用户名
	 * @return
	 * @throws NetException
	 * @throws Exception
	 */
	List<User> query(String username) throws NetException, Exception;

}
