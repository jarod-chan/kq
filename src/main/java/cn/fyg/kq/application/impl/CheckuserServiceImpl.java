package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.kq.checkuser.Checkuser;
import cn.fyg.kq.domain.model.kq.checkuser.CheckuserRepository;

@Service
public class CheckuserServiceImpl extends SericeQueryImpl<Checkuser> implements CheckuserService {

	@Autowired
	CheckuserRepository checkuserRepository;

	@Override
	@Transactional
	public Checkuser save(Checkuser checkuser) {
		return this.checkuserRepository.save(checkuser);
	}

	@Override
	public List<Checkuser> findAll() {
		return this.checkuserRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(String fid) {
		this.checkuserRepository.delete(fid);
	}

	@Override
	public Checkuser find(String fid) {
		return this.checkuserRepository.findOne(fid);
	}

	@Override
	public JpaSpecificationExecutor<Checkuser> getSpecExecutor() {
		return this.checkuserRepository;
	}

}
