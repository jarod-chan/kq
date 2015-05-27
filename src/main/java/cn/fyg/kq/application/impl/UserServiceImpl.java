package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.UserService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.model.user.UserFactory;
import cn.fyg.kq.domain.model.user.UserRepository;

@Service
public class UserServiceImpl extends SericeQueryImpl<User> implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(String fid) {
		this.userRepository.delete(fid);
	}

	@Override
	public User find(String fid) {
		return this.userRepository.findOne(fid);
	}

	@Override
	public User create() {
		return UserFactory.create();
	}

	@Override
	public User findByFnumber(String fnumber) {
		return this.userRepository.findByFnumber(fnumber);
	}

	@Override
	public boolean exists(String fid) {
		return this.userRepository.exists(fid);
	}

	@Override
	public JpaSpecificationExecutor<User> getSpecExecutor() {
		return this.userRepository;
	}

	


}
