package cn.fyg.kq.interfaces.web.module.hr.leave;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CompdateService;
import cn.fyg.kq.application.LeaveService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.opinion.Result;
import cn.fyg.kq.domain.model.vacation.common.AMPM;
import cn.fyg.kq.domain.model.vacation.common.BusiState;
import cn.fyg.kq.domain.model.vacation.common.LeaveType;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.CustomEditorFactory;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;
import cn.fyg.module.user.User;

@Controller
@RequestMapping("hr/leave")
public class LeaveCtl {
	
	private static final String PATH = "hr/leave/";
	private interface Page {
		String START = PATH + "start";
		String CHECK = PATH + "check";
		String EDIT = PATH + "edit";
	}
	
	@Autowired
	LeaveService leaveService;
	@Autowired
	CompdateService compdateService;
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	OpinionService opinionService;
	

	@RequestMapping(value="start",method=RequestMethod.GET)
	public String toStart(Map<String,Object> map,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		User user=sessionUtil.getUser();
		Leave leave = leaveService.create(user);
		map.put("leave", leave);
		map.put("leaveTypes", LeaveType.values());
		map.put("ampms", AMPM.values());
		map.put(FlowConstant.PROCESS_DEFINITION_KEY, processDefinitionKey);
		return Page.START;
	}
	
	@RequestMapping(value="start/commit",method=RequestMethod.POST)
	public String startCommit(HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		User user=sessionUtil.getUser();
		Leave leave = leaveService.create(user);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(leave);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(leave.getBegDayitem(), leave.getEndDayitem());
		leave.setNatureDay(dayResult.natureDay());
		leave.setActurlDay(dayResult.acturlDay());
		leave.setBusiState(BusiState.execute);
		leaveService.save(leave);
		
		try{
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put(FlowConstant.BUSINESS_ID, leave.getId());
			variableMap.put(FlowConstant.APPLY_USER, user.getKey());
			identityService.setAuthenticatedUserId(user.getKey());
			runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);			
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("请假流程启动！"));
		return "redirect:/process/start";
	}
	
	@RequestMapping(value="check/{businessId}",method=RequestMethod.GET)
	public String toCheck(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Leave leave = leaveService.find(businessId);
		map.put("leave", leave);
		map.put("resultList", Result.agreeItems());
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		return Page.CHECK;
	}
	
	@RequestMapping(value="check/commit",method=RequestMethod.POST)
	public String checkCommit(Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		User user=sessionUtil.getUser();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		opinion.setBusinessCode(Leave.BUSINESS_CODE);
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		opinion.setUserKey(user.getKey());
		opinion.setUserName(user.getRealname());
		opinionService.append(opinion);
		runtimeService.setVariableLocal(task.getExecutionId(), LeaveVarName.IS_AGGREE,opinion.getResult().<Boolean>val());
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="edit/{businessId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Leave leave = leaveService.find(businessId);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		List<Opinion> opinionList = opinionService.allOpinion(Leave.BUSINESS_CODE, businessId);
		map.put("leave", leave);
		map.put("leaveTypes", LeaveType.values());
		map.put("ampms", AMPM.values());
		map.put("task", task);
		map.put("opinionList", opinionList);
		return Page.EDIT;
	}
	
	@RequestMapping(value="edit/save",method=RequestMethod.POST)
	public String editSave(@RequestParam("id")Long id,HttpServletRequest request,RedirectAttributes redirectAttributes){
		Leave leave = leaveService.find(id);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(leave);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(leave.getBegDayitem(), leave.getEndDayitem());
		leave.setNatureDay(dayResult.natureDay());
		leave.setActurlDay(dayResult.acturlDay());
		leaveService.save(leave);
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("保存成功！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="edit/commit",method=RequestMethod.POST)
	public String editCommit(@RequestParam("id")Long id,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		Leave leave = leaveService.find(id);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(leave);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(leave.getBegDayitem(), leave.getEndDayitem());
		leave.setNatureDay(dayResult.natureDay());
		leave.setActurlDay(dayResult.acturlDay());
		leaveService.save(leave);
		taskService.complete(taskId);
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}

}
