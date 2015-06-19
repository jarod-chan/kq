package cn.fyg.kq.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.NotiService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.application.common.impl.SericeQueryImpl;
import cn.fyg.kq.domain.model.noti.Noti;
import cn.fyg.kq.domain.model.noti.NotiFactory;
import cn.fyg.kq.domain.model.noti.NotiRepository;
import cn.fyg.kq.domain.model.noti.ReadStat;
import cn.fyg.kq.domain.model.user.User;

@Service("notiService")
public class NotiServiceImpl extends SericeQueryImpl<Noti> implements NotiService {
	
	@Autowired
	NotiRepository notiRepository;
	@Autowired
	UserService userService;

	@Override
	public JpaSpecificationExecutor<Noti> getSpecExecutor() {
		return this.notiRepository;
	}

	@Override
	public Noti find(Long notiId) {
		return this.notiRepository.findOne(notiId);
	}

	@Override
	public String url(String str, String url) {
		return String.format("<a href=\"javascript:void(0);\" onclick=\"javascript:openwin(this)\" data-url=\"%s\">%s</a>",url,str);
	}

	@Override
	@Transactional
	public Noti send(String userid, String message) {
		User user = this.userService.find(userid);
		Noti noti = NotiFactory.create(user, message);
		return this.notiRepository.save(noti);
	}

	@Override
	@Transactional
	public boolean read(Long notiId) {
		Noti noti = this.notiRepository.findOne(notiId);
		if(noti.getStat()==ReadStat.y){
			return false;
		}
		noti.setStat(ReadStat.y);
		this.notiRepository.save(noti);
		return true;
	}

}
