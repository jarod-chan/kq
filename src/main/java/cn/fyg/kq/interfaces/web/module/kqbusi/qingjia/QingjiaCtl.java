package cn.fyg.kq.interfaces.web.module.kqbusi.qingjia;

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

import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.application.QingjiaService;
import cn.fyg.kq.domain.model.opinion.OpResult;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.qingjia.Qingjia;
import cn.fyg.kq.domain.model.qingjia.QingjiaState;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Ampm;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

@Controller
@RequestMapping("qingjia")
public class QingjiaCtl {
	
	private static final String PATH = "qingjia/";
	private interface Page {
		String LIST = PATH + "list";
		String EDIT = PATH + "edit";
		String CHECK = PATH + "check";
	}
	
	@Autowired
	QingjiaService qingjiaService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Qingjia> qingjiaList = this.qingjiaService.findAll();
		map.put("qingjiaList", qingjiaList);
		return Page.LIST;
	}
	
	@RequestMapping(value="{qingjiaId}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("qingjiaId") Long qingjiaId,Map<String,Object> map){
		User user=new User();
		user.setFid("u2");
		String comp="jianshe";
		Qingjia qingjia =qingjiaId.longValue()>0?qingjiaService.find(qingjiaId):qingjiaService.create(user,QingjiaState.create,comp) ;
		map.put("qingjia", qingjia);
		map.put("ampms", Ampm.values());
		return Page.EDIT;
	}
	
	@RequestMapping(value="saveEdit",method=RequestMethod.POST)
	public String saveEdit(@RequestParam("qingjiaId")Long qingjiaId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user=new User();
		user.setFid("u2");
		String comp="jianshe";
		
		Qingjia qingjia =qingjiaId!=null?qingjiaService.find(qingjiaId):qingjiaService.create(user,QingjiaState.save,comp) ;
		
		BindTool.bindRequest(qingjia, request);
		qingjia=qingjiaService.save(qingjia);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	
	}
	
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	
	
	/**
	 * 提交启动流程
	 * @param qingjiaId
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="saveCommit",method=RequestMethod.POST)
	public String saveCommit(@RequestParam("qingjiaId")Long qingjiaId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		User user=new User();
		user.setFid("u2");
		String comp="jianshe";
		
		Qingjia qingjia =qingjiaId!=null?qingjiaService.find(qingjiaId):qingjiaService.create(user,QingjiaState.save,comp) ;
		
		BindTool.bindRequest(qingjia, request);
		qingjia=qingjiaService.save(qingjia);
		
		try{
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put(FlowConstant.BUSINESS_ID, qingjia.getId());
			variableMap.put(FlowConstant.APPLY_USER, user.getFid());
			identityService.setAuthenticatedUserId(user.getFid());
			runtimeService.startProcessInstanceByKey("fyg-kq-qingjia", variableMap);			
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
	
	@RequestMapping(value="check/{businessId}",method=RequestMethod.GET)
	public String toCheck(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Qingjia qingjia = qingjiaService.find(businessId);
		map.put("qingjia", qingjia);
		map.put("resultList", OpResult.agreeItems());
		map.put("ampms", Ampm.values());
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		map.put("task", task);
		return Page.CHECK;
	}

	@RequestMapping(value="check/commit",method=RequestMethod.POST)
	public String checkCommit(Opinion opinion,Map<String,Object> map,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		
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
			.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("任务完成！"));
		return "redirect:/process/task";
	}
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("qingjiaId") Long qingjiaId){
		qingjiaService.delete(qingjiaId);
		return "redirect:list";
	}

}
