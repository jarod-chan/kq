package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin;

import static cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs.isFinish;
import static cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs.notFinish;
import static cn.fyg.kq.interfaces.web.shared.message.Message.error;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.application.facade.KaoqinFacade;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.domain.model.kaoqin.busi.PassState;
import cn.fyg.kq.domain.model.opinion.OpResult;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.verify.Result;
import cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin.flow.KaoqinVarname;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

@Controller
@RequestMapping("kaoqin")
public class KaoqinCtl {
	
	private static final String PATH = "kaoqin/";
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
		Specifications<Kaoqin> specs = Specifications.where(notFinish());
		Sort sort=new Sort(Direction.DESC,"id");
		List<Kaoqin> notFinishList = this.kaoqinService.findAll(specs,sort);
		map.put("notFinishList", notFinishList);
		
		specs = Specifications.where(isFinish());
		List<Kaoqin> isFinishList = this.kaoqinService.findAll(specs,sort);
		map.put("isFinishList", isFinishList);
	
		return Page.LIST;
	}
	
	@RequestMapping(value="{kaoqinId}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("kaoqinId")Long kaoqinId,Map<String,Object> map){
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		map.put("kaoqin", kaoqin);
		return Page.EDIT;
	}
	
	@Autowired
	KaoqinFacade kaoqinFacade;
	
	@RequestMapping(value="saveEdit",method=RequestMethod.POST)
	public String saveEdit(@RequestParam("kaoqinId")Long kaoqinId,@RequestParam("afteraction")String afteraction,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user=new User();

		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId); 
		
		//TODO 使用当前用户
		user=kaoqin.getUser();
		
		BindTool.bindRequest(kaoqin, request);
		kaoqin.setState(KaoqinState.save);
		kaoqin=kaoqinService.save(kaoqin);
		
		if(afteraction.equals("save")){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
			return "redirect:list";
		}
		
		if(afteraction.equals("commit")){
			Result result=this.kaoqinFacade.commit(kaoqin, user);
			if(result.notPass()){
				redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("提交失败！%s",result.message()));
				return String.format("redirect:%s/edit",kaoqin.getId());
			}else{				
				redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("提交成功！"));
				return "redirect:list";
			}
		}
		
		return "";
	
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
			variableMap.put(FlowConstant.BUSINESS_NO, kaoqin.getNo());
			variableMap.put(FlowConstant.APPLY_USER, kaoqin.getUser().getFid());
			variableMap.put(FlowConstant.BUSINESS_TITLE, kaoqin.getMonthitem().getYear()+"年"+kaoqin.getMonthitem().getMonth()+"月"+kaoqin.getUser().getFnumber()+"考勤单");
			variableMap.put("item_all", kaoqin.getItem_all());
			identityService.setAuthenticatedUserId(user.getFid());
			runtimeService.startProcessInstanceByKey(KaoqinVarname.PROCESS_DEFINITION_KEY, variableMap);			
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
		map.put("resultList", OpResult.agreeItems());
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
		opinion.setBusinessCode(Kaoqin.BUSI_CODE);
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		opinion.setUserKey("chenzw");
		opinion.setUserName("username");
		opinionService.append(opinion);
		runtimeService.setVariable(task.getExecutionId(), KaoqinVarname.IS_AGGREE,Boolean.TRUE);
		taskService.complete(task.getId());
		redirectAttributes
			.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="{businessId}/checkedit",method=RequestMethod.GET)
	public String tocheckEdit(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Kaoqin kaoqin = this.kaoqinService.find(businessId);
		map.put("kaoqin", kaoqin);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		
		List<Opinion> opinionList = opinionService.allOpinion(Kaoqin.BUSI_CODE, businessId);
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
			.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}

}
