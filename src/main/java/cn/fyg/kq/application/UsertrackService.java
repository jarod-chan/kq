package cn.fyg.kq.application;

import java.util.Set;

public interface UsertrackService {
	
	void trackProcessInstance(String userId,String processInstanceId);
	
	void untrackProcessInstance(String userId,String processInstanceId);
	
	Set<String> getTrackProcessInstanceIds(String userId);

}
