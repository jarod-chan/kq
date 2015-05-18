package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserRepository;
import cn.fyg.kq.domain.model.checkuser.Kqstat;

@Service
public class CheckuserServiceImpl extends SericeQueryImpl<Checkuser> implements CheckuserService {

	@Autowired
	CheckuserRepository checkuserRepository;

	@Override
	@Transactional
	public Checkuser save(Checkuser checkuser) {
		if(checkuser.getKqstat()!=Kqstat.yes){
			checkuser.setUserid(null);
			checkuser.setBadgenumber(null);
			checkuser.setName(null);
		}
		return this.checkuserRepository.save(checkuser);
	}

	@Override
	public List<Checkuser> findAll() {
		return this.checkuserRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.checkuserRepository.delete(id);
	}

	@Override
	public Checkuser find(Long id) {
		return this.checkuserRepository.findOne(id);
	}

	@Override
	public JpaSpecificationExecutor<Checkuser> getSpecExecutor() {
		return this.checkuserRepository;
	}

}
