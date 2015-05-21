package cn.fyg.kq.interfaces.web.module.process.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

@Controller
@RequestMapping("process/task")
public class TaskCtl {
	
	private static final String PATH = "process/task/";
	private interface Page {
		String TASK = PATH + "task";
	}
	
	@Autowired
	TaskAssembler taskAssembler;
	@Autowired
	SessionUtil sessionUtil;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		//TODO 修改成对应人员
		List<ProcessTask> processTaskList = taskAssembler.getProcessTasks("chenzw");
		map.put("processTaskList", processTaskList);
		return Page.TASK;
	}

}
