package cn.fyg.kq.interfaces.web.module.process.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.module.user.User;

@Controller
@RequestMapping("process/task")
public class TaskCtl {
	
	private static final String PATH = "process/task/";
	private interface Page {
		String TASK = PATH + "task";
	}
	
	@Autowired
	TaskFacade taskFacade;
	@Autowired
	SessionUtil sessionUtil;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user = sessionUtil.getUser();
		List<ProcessTaskBean> processTasks = taskFacade.getProcessTasks(user.getKey());
		map.put("processTasks", processTasks);
		return Page.TASK;
	}

}
