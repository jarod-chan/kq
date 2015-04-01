package cn.fyg.kq.interfaces.web.module.general;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.UsertrackService;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.module.user.User;

@Controller
@RequestMapping("/general")
public class GeneralCtl {
	
	private static final String PATH = "general/";
	private interface Page {
		String TASK = PATH + "task";
	}
	
	@Autowired
	TaskService taskService;
	@Autowired
	UsertrackService usertrackService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="/task",method=RequestMethod.GET)
	public String toTask(Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		return Page.TASK;
	}
	
	@RequestMapping(value="/complete",method=RequestMethod.POST)
	public String complete(RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
	
		//TODO 跟踪流程逻辑
		User user=sessionUtil.getValue("user");
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		usertrackService.trackProcessInstance(user.getKey(),task.getProcessInstanceId());
		
		
		taskService.complete(taskId);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("任务完成！"));
		return "redirect:/process/task";
	}

}
