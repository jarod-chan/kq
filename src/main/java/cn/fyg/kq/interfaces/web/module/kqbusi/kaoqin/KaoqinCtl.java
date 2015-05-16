package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kq.kaoqin.PassState;
import cn.fyg.kq.domain.model.kq.user.User;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.opinion.Result;
import cn.fyg.kq.interfaces.web.module.kqbusi.qingjia.LeaveVarName;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

@Controller
@RequestMapping("kaoqin")
public class KaoqinCtl {
	
	private static final String PATH = "kq/kaoqin/";
	private interface Page {
		String LIST = PATH + "list";
		String EDIT = PATH + "edit";
		String CHECK = PATH + "check";
		String CHECK_EDIT = PATH + "check_edit";
	}
	
	@Autowired
	KaoqinService kaoqinService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll();
		map.put("kaoqinList", kaoqinList);
		return Page.LIST;
	}
	
	@RequestMapping(value="{kaoqinId}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("kaoqinId")Long kaoqinId,Map<String,Object> map){
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		map.put("kaoqin", kaoqin);
		return Page.EDIT;
	}
	
	@RequestMapping(value="saveEdit",method=RequestMethod.POST)
	public String saveEdit(@RequestParam("kaoqinId")Long kaoqinId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user=new User();
		user.setFid("u2");
		String comp="jianshe";
		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	
	}
	
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	
	//提交流程
	@RequestMapping(value="saveCommit",method=RequestMethod.POST)
	public String saveCommit(@RequestParam("kaoqinId")Long kaoqinId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user=new User();
		user.setFid("u2");
		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		
		try{
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put(FlowConstant.BUSINESS_ID, kaoqin.getId());
			variableMap.put(FlowConstant.APPLY_USER, kaoqin.getUser().getFid());
			variableMap.put("item_all", kaoqin.getItem_all());
			identityService.setAuthenticatedUserId(user.getFid());
			runtimeService.startProcessInstanceByKey("fyg-kq-kaoqin", variableMap);			
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("流程启动"));
		return "redirect:list";
	
	}
	
	
	
	@Autowired
	SessionUtil sessionUtil;
	
	@Autowired
	OpinionService opinionService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserService userService;
	
	//流程审批
	@RequestMapping(value="{businessId}/check",method=RequestMethod.GET)
	public String toCheck(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){

		Kaoqin kaoqin = this.kaoqinService.find(businessId);
		map.put("kaoqin", kaoqin);
		map.put("resultList", Result.agreeItems());
		map.put("PassStates", PassState.values());
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String assignee = task.getAssignee();
		
		User user = this.userService.find(assignee);
		map.put("user", user);
		
		map.put("task", task);
		return Page.CHECK;
	}
	
	@RequestMapping(value="check/commit",method=RequestMethod.POST)
	public String checkCommit(@RequestParam("kaoqinId")Long kaoqinId,HttpServletRequest request,Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		opinion.setBusinessCode(LeaveVarName.BUSINESS_CODE);
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		opinion.setUserKey("chenzw");
		opinion.setUserName("username");
		opinionService.append(opinion);
		runtimeService.setVariableLocal(task.getExecutionId(), LeaveVarName.IS_AGGREE,opinion.getResult().<Boolean>val());
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="{businessId}/checkedit",method=RequestMethod.GET)
	public String tocheckEdit(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Kaoqin kaoqin = this.kaoqinService.find(businessId);
		map.put("kaoqin", kaoqin);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		
		List<Opinion> opinionList = opinionService.allOpinion(LeaveVarName.BUSINESS_CODE, businessId);
		map.put("opinionList", opinionList);
		return Page.CHECK_EDIT;
	}
	
	@RequestMapping(value="checkedit/commit",method=RequestMethod.POST)
	public String editCommit(@RequestParam("kaoqinId")Long kaoqinId,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){

		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}

}
