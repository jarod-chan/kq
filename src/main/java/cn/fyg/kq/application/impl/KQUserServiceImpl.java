package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.KQUserService;
import cn.fyg.kq.domain.model.md.KQUser;
import cn.fyg.kq.domain.model.md.KQUserFactory;
import cn.fyg.kq.domain.model.md.KQUserRepository;

@Service
public class KQUserServiceImpl implements KQUserService {
	
	@Autowired
	KQUserRepository kqUserRepository;
	
	@Override
	@Transactional
	public void init(String fid) throws Exception {
		if(this.kqUserRepository.exists(fid)){
			throw new Exception("用户已经初始化");
		}
		KQUser kqUser = KQUserFactory.create(fid);
		this.kqUserRepository.save(kqUser);
	}

}
