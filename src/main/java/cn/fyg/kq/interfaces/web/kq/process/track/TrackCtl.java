package cn.fyg.kq.interfaces.web.kq.process.track;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.application.UsertrackService;
import cn.fyg.kq.domain.model.kq.user.User;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;


@Controller
@RequestMapping("process/track")
public class TrackCtl {
	
	private static final String PATH = "process/track/";
	
	private interface Page{
		String TRACK=PATH+"track";
	}

	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	UsertrackService usertrackService;
	@Autowired
	RuntimeService runtimeService;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Set<String> processInstanceIds = usertrackService.getTrackProcessInstanceIds("chenzw");
		if(!processInstanceIds.isEmpty()){			
			List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery()
					.processInstanceIds(processInstanceIds)
					.orderByProcessInstanceId()
					.asc().list();
			map.put("processInstanceList", processInstanceList);
		}
		return Page.TRACK;
	}

}
