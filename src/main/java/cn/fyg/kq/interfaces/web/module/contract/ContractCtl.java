package cn.fyg.kq.interfaces.web.module.contract;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ContractService;
import cn.fyg.kq.application.UsertrackService;
import cn.fyg.kq.domain.model.contract.Contract;
import cn.fyg.kq.domain.model.contract.ContractFactory;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;
import cn.fyg.kq.interfaces.web.shared.tool.Constant;
import cn.fyg.module.user.User;

@Controller
@RequestMapping("/contract")
public class ContractCtl {
	
	private static final String PATH = "contract/";
	private interface Page {
		String EDIT = PATH + "edit";
		String VIEW = PATH + "view";
		String START= PATH + "start";
	}
	
	@Autowired
	ContractService contractService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	IdentityService identityService;
	@Autowired
	UsertrackService usertrackService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toNew(Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Contract contract = ContractFactory.create();
		map.put("contract", contract);
		map.put("taskId", taskId);
		return Page.EDIT;
	}
	
	@RequestMapping(value="{businessId}",method=RequestMethod.GET)
	public String toEdit(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Contract contract = contractService.find(businessId);
		map.put("contract", contract);
		map.put("taskId", taskId);
		return Page.EDIT;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Contract contract,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		contract = contractService.save(contract);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		User user=sessionUtil.getValue("user");
		usertrackService.trackProcessInstance(user.getKey(),task.getProcessInstanceId());
		
		runtimeService.setVariableLocal(task.getExecutionId(), "businessId", contract.getId());
		redirectAttributes
			.addAttribute("businessId", contract.getId())
			.addAttribute("taskId", taskId)
			.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("合同保存成功！"));
		
		return "redirect:/contract/{businessId}?taskId={taskId}";
	}
	
	@RequestMapping(value="commit",method=RequestMethod.POST)
	public String commit(Contract contract,RedirectAttributes redirectAttributes,@RequestParam(value="taskId",required=false)String taskId){
		contract = contractService.save(contract);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		User user=sessionUtil.getValue("user");
		usertrackService.trackProcessInstance(user.getKey(),task.getProcessInstanceId());
		
		runtimeService.setVariableLocal(task.getExecutionId(), "businessId", contract.getId());
		taskService.complete(taskId);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("合同提交成功！"));
		
		return "redirect:/process/task";
	}
	
	
	@RequestMapping(value="view/{businessId}",method=RequestMethod.GET)
	public String toView(@PathVariable(value="businessId")Long businessId,Map<String,Object> map,@RequestParam(value="taskId",required=false)String taskId){
		Contract contract = contractService.find(businessId);
		map.put("contract", contract);
		map.put("taskId", taskId);
		return Page.VIEW;
	}
	
	@RequestMapping(value="check",method=RequestMethod.POST)
	public String check(RedirectAttributes redirectAttributes,@RequestParam("leaderPass")Boolean leaderPass,@RequestParam(value="taskId",required=false)String taskId){
		User user=sessionUtil.getValue("user");
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		usertrackService.trackProcessInstance(user.getKey(),task.getProcessInstanceId());
		
		
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("leaderPass", leaderPass);
		taskService.complete(taskId, variableMap);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("合同已处理！"));
		return "redirect:/process/task";
	}
	
	@RequestMapping(value="start",method=RequestMethod.GET)
	public String toStart(Map<String,Object> map,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		Contract contract = ContractFactory.create();
		map.put("contract", contract);
		map.put("processDefinitionKey", processDefinitionKey);
		return Page.START;
	}
	
	@RequestMapping(value="start",method=RequestMethod.POST)
	public String start(Contract contract,RedirectAttributes redirectAttributes,@RequestParam(value="processDefinitionKey",required=false)String processDefinitionKey){
		User user=sessionUtil.getValue("user");
		contract = contractService.save(contract);
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("businessId", contract.getId());
		variableMap.put("applyUser", user.getKey());
		identityService.setAuthenticatedUserId(user.getKey());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, Message.create().info().message("流程[%s]已启动！",processDefinitionKey));
		usertrackService.trackProcessInstance(user.getKey(),processInstance.getProcessInstanceId());
		return "redirect:/process/start";
	}
}
