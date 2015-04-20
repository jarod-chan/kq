package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	public void init(String fid,String fnumber,String fname,int userid) throws Exception {		
		if(this.kqUserRepository.exists(fid)){
			throw new Exception("用户已经初始化");
		}
		KQUser kqUser = KQUserFactory.create(fid,fnumber,fname,userid);
		this.kqUserRepository.save(kqUser);
	}

	@Override
	public boolean isInit(String fid) {
		return this.kqUserRepository.exists(fid);
	}

	@Override
	public List<KQUser> all() {
		return this.kqUserRepository.findAll(new Sort(Sort.Direction.DESC,"createtime"));
	}

	@Override
	@Transactional
	public void delete(String fid) {
		this.kqUserRepository.delete(fid);
	}

}
