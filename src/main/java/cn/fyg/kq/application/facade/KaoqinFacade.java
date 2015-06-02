package cn.fyg.kq.application.facade;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.verify.Result;
import cn.fyg.kq.infrastructure.tool.date.DateUtil;
import cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow.KaoqinVarname;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;


@Component
public class KaoqinFacade {
	
	@Autowired
	KaoqinService kaoqinService;
	@Autowired
	IdentityService identityService;
	@Autowired
	TaskService taskService;
	@Autowired
	RuntimeService runtimeService;
	
	@Transactional
	public Result commit(Kaoqin kaoqin, User user) {
		Result result = this.kaoqinService.verifyForCommit(kaoqin);
		if(result.notPass()) return result;
		kaoqin.setState(KaoqinState.process);
		kaoqin=this.kaoqinService.save(kaoqin);
		String userFid=user.getFid();
		try{
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put(FlowConstant.BUSINESS_ID, kaoqin.getId());
			variableMap.put(FlowConstant.BUSINESS_NO, kaoqin.getNo());
			variableMap.put(FlowConstant.APPLY_USER, userFid);
			variableMap.put(FlowConstant.BUSINESS_TITLE, kaoqin.getTitle());
			variableMap.put(KaoqinVarname.ITEM_ALL, kaoqin.getItem_all());
//TODO			到期时间设定
			variableMap.put("dm_time", DateUtil.minute(1));
			variableMap.put("vp_time", DateUtil.minute(2));
			variableMap.put("gm_time", DateUtil.minute(3));
			
			identityService.setAuthenticatedUserId(userFid);
			runtimeService.startProcessInstanceByKey(KaoqinVarname.PROCESS_DEFINITION_KEY, variableMap);			
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		
		return result;
	}
	
	@Transactional
	public Result commitCheck(Kaoqin kaoqin,User user,String taskId){
		Result result = this.kaoqinService.verifyForCommit(kaoqin);
		if(result.notPass()) return result;
		try{
			identityService.setAuthenticatedUserId(user.getFid());
			taskService.complete(taskId);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return result;
	}

}
