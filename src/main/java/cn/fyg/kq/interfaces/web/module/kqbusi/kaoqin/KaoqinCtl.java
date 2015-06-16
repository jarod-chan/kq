package cn.fyg.kq.interfaces.web.module.kqbusi.kaoqin;

import static cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs.isFinish;
import static cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs.notFinish;
import static cn.fyg.kq.domain.model.kaoqin.KaoqinSpecs.ofUser;
import static cn.fyg.kq.interfaces.web.shared.message.Message.error;
import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

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
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
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
		String VIEW = PATH + "view";
	}
	
	@Autowired
	KaoqinService kaoqinService;
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	OpinionService opinionService;
	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	KaoqinFacade kaoqinFacade;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Specifications<Kaoqin> specs = Specifications.where(notFinish()).and(ofUser(user));
		Sort sort=new Sort(Direction.DESC,"id");
		List<Kaoqin> notFinishList = this.kaoqinService.findAll(specs,sort);
		map.put("notFinishList", notFinishList);
		
		specs = Specifications.where(isFinish()).and(ofUser(user));
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
	
	@RequestMapping(value="saveEdit",method=RequestMethod.POST)
	public String saveEdit(@RequestParam("kaoqinId")Long kaoqinId,@RequestParam("afteraction")String afteraction,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		User user=sessionUtil.getValue("user");
		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId); 
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
	
	@RequestMapping(value="{kaoqinId}/view",method=RequestMethod.GET)
	public String toView(@PathVariable("kaoqinId")Long kaoqinId,Map<String,Object> map){
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		map.put("kaoqin", kaoqin);
		List<Opinion> opinions = opinionService.listOpinions(Kaoqin.BUSI_CODE, kaoqinId);
		map.put("opinions", opinions);
		return Page.VIEW;
	}

	
	//流程审批
	@RequestMapping(value="{businessId}/check",method=RequestMethod.GET)
	public String toCheck(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){

		Kaoqin kaoqin = this.kaoqinService.find(businessId);
		map.put("kaoqin", kaoqin);
		map.put("resultList", OpResult.checkItems());
		map.put("PassStates", PassState.values());
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		
		String assignee = task.getAssignee();
		User user = this.userService.find(assignee);
		map.put("user", user);
		
		return Page.CHECK;
	}
	
	@RequestMapping(value="check/commit",method=RequestMethod.POST)
	public String checkCommit(@RequestParam("kaoqinId")Long kaoqinId,HttpServletRequest request,Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		

		User user=sessionUtil.getValue("user");
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		opinion.setBusinessCode(Kaoqin.BUSI_CODE);
		
		opinion.setTaskId(task.getId());
		opinion.setTaskKey(task.getTaskDefinitionKey());
		opinion.setTaskName(task.getName());
		
		opinion.setUserKey(user.getFid());
		opinion.setUserName(user.getFnumber());
		opinionService.append(opinion);

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
		
		List<Opinion> opinionList = opinionService.listOpinions(Kaoqin.BUSI_CODE, businessId);
		map.put("opinions", opinionList);
		return Page.CHECK_EDIT;
	}
	
	@RequestMapping(value="checkedit/save",method=RequestMethod.POST)
	public String saveCheckedit(@RequestParam("kaoqinId")Long kaoqinId,@RequestParam("afteraction")String afteraction,HttpServletRequest request,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){

		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		BindTool.bindRequest(kaoqin, request);
		kaoqin=kaoqinService.save(kaoqin);
		
		if(afteraction.equals("save")){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
			return "redirect:/process/task";
		}
		if(afteraction.equals("commit")){
			User user = sessionUtil.getValue("user");
			Result result =this.kaoqinFacade.commitCheck(kaoqin,user,taskId);
			if(result.notPass()){
				redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("提交失败！"+result.message()));
				return String.format("redirect:%s/checkedit?taskId",kaoqin.getId(),taskId);
			}else{				
				redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("提交成功！"));
				return "redirect:/process/task";
			}	
		}
		
		return "";
	}

}
