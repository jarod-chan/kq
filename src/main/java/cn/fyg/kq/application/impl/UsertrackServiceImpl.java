package cn.fyg.kq.application.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.UsertrackService;
import cn.fyg.kq.domain.model.usertrack.Usertrack;
import cn.fyg.kq.domain.model.usertrack.UsertrackRepository;

@Service("usertrackService")
public class UsertrackServiceImpl implements UsertrackService {

	@Autowired
	UsertrackRepository usertrackRepository;
	
	@Override
	@Transactional
	public void trackProcessInstance(String userId,String processInstanceId) {
		Usertrack usertrack = usertrackRepository.findByUserIdAndProcessInstanceId(userId, processInstanceId);
		if(usertrack==null){
			usertrack=new Usertrack();
			usertrack.setUserId(userId);
			usertrack.setProcessInstanceId(processInstanceId);
			usertrackRepository.save(usertrack);
		}
	}

	@Override
	@Transactional
	public void untrackProcessInstance(String userId, String processInstanceId) {
		Usertrack usertrack = usertrackRepository.findByUserIdAndProcessInstanceId(userId, processInstanceId);
		if(usertrack==null){
			 throw new NullPointerException("usertrack not exist");
		}
		usertrackRepository.delete(usertrack);
	}

	@Override
	public Set<String> getTrackProcessInstanceIds(String userId) {
		List<Usertrack> usertrackList = usertrackRepository.findByUserIdOrderByIdAsc(userId);
		Set<String> processInstanceIds=new HashSet<String>();
		for (Usertrack usertrack : usertrackList) {
			processInstanceIds.add(usertrack.getProcessInstanceId());
		}
		return processInstanceIds;
	}

}
