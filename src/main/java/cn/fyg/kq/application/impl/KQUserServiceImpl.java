package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.fyg.kq.application.KQUserService;
import cn.fyg.kq.domain.model.md.KQUser;

@Service
public class KQUserServiceImpl implements KQUserService {

	@Override
	public void init(String fid, String fnumber, String fname, int userid)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInit(String fid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<KQUser> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String fid) {
		// TODO Auto-generated method stub
		
	}
	
/*	@Autowired
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
*/
}
