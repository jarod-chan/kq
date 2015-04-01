package cn.fyg.kq.interfaces.web.module.hr.back;

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

import cn.fyg.kq.application.BackService;
import cn.fyg.kq.application.CompdateService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.opinion.Result;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.kq.domain.model.vacation.common.AMPM;
import cn.fyg.kq.domain.model.vacation.common.LeaveType;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.CustomEditorFactory;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;
import cn.fyg.module.user.User;

@Controller
@RequestMapping("hr/back")
public class BackCtl {
	
	private static final String PATH = "hr/back/";
	private interface Page {
		String START = PATH + "start";
		String CHECK_DM = PATH + "check_dm";
		String CHECK_VP = PATH + "check_vp";
		String EDIT = PATH + "edit";
	}
	
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	BackService backService;
	@Autowired
	TaskService taskService;
	@Autowired
	OpinionService opinionService;
	@Autowired
	CompdateService compdateService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="start",method=RequestMethod.GET)
	public String toStart(Map<String,Object> map,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		User user=sessionUtil.getUser();
		Back back=backService.create(user);
		map.put("back", back);
		map.put("leaveTypes", LeaveType.values());
		map.put("ampms", AMPM.values());
		map.put(FlowConstant.PROCESS_DEFINITION_KEY, processDefinitionKey);
		return Page.START;
	}
	
	@RequestMapping(value="start/commit",method=RequestMethod.POST)
	public String startCommit(HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		User user=sessionUtil.getUser();
		Back back=backService.create(user);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(back);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(back.getBegDayitem(), back.getEndDayitem());
		back.setNatureDay(dayResult.natureDay());
		back.setActurlDay(dayResult.acturlDay());
		backService.save(back);
		
		try{
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put(FlowConstant.BUSINESS_ID, back.getId());
			variableMap.put(FlowConstant.APPLY_USER, user.getKey());
			identityService.setAuthenticatedUserId(user.getKey());
			runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);			
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("销假流程启动！"));
		return "redirect:/process/start";
	}
	
	@RequestMapping(value="check_dm/{businessId}",method=RequestMethod.GET)
	public String toCheckdm(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Back back = backService.find(businessId);
		map.put("back", back);
		map.put("resultList", Result.passItems());
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		return Page.CHECK_DM;
	}
	
	@RequestMapping(value="check_dm/commit",method=RequestMethod.POST)
	public String checkdmCommit(Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		User user=sessionUtil.getUser();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		opinion.setBusinessCode(Back.BUSINESS_CODE);
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		opinion.setUserKey(user.getKey());
		opinion.setUserName(user.getRealname());
		opinionService.append(opinion);
		
		runtimeService.setVariableLocal(task.getExecutionId(), BackVarName.IS_PASS,opinion.getResult().<Boolean>val());
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("任务完成！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value={"check_vp/{businessId}","check_gm/{businessId}"},method=RequestMethod.GET)
	public String toCheckvp(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Back back = backService.find(businessId);
		map.put("back", back);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		return Page.CHECK_VP;
	}
	
	@RequestMapping(value="check_vp/commit",method=RequestMethod.POST)
	public String checkvpCommit(Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		User user=sessionUtil.getUser();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		opinion.setBusinessCode(Back.BUSINESS_CODE);
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		opinion.setUserKey(user.getKey());
		opinion.setUserName(user.getRealname());
		opinionService.append(opinion);
		
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("任务完成！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="edit/{businessId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Back back = backService.find(businessId);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		List<Opinion> opinionList = opinionService.allOpinion(Back.BUSINESS_CODE, businessId);
		map.put("back", back);
		map.put("leaveTypes", LeaveType.values());
		map.put("ampms", AMPM.values());
		map.put("task", task);
		map.put("opinionList", opinionList);
		return Page.EDIT;
	}
	
	@RequestMapping(value="edit/save",method=RequestMethod.POST)
	public String editSave(@RequestParam("id")Long id,HttpServletRequest request,RedirectAttributes redirectAttributes){
		Back back = backService.find(id);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(back);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(back.getBegDayitem(), back.getEndDayitem());
		back.setNatureDay(dayResult.natureDay());
		back.setActurlDay(dayResult.acturlDay());
		backService.save(back);
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("保存成功！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="edit/commit",method=RequestMethod.POST)
	public String editCommit(@RequestParam("id")Long id,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		Back back = backService.find(id);
		ServletRequestDataBinder binder = new ServletRequestDataBinder(back);
        binder.registerCustomEditor(Date.class,CustomEditorFactory.getCustomDateEditor());
		binder.bind(request);
		DayResult dayResult = compdateService.computerDay(back.getBegDayitem(), back.getEndDayitem());
		back.setNatureDay(dayResult.natureDay());
		back.setActurlDay(dayResult.acturlDay());
		backService.save(back);
		taskService.complete(taskId);
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("任务完成！"));
		return "redirect:/process/task";
	}

}
