package cn.fyg.kq.interfaces.web.module.process.task;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

@Component
public class TaskAssembler {
	
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	FormService formService;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	HistoryService historyService;
	
	
	public List<ProcessTask> getProcessTasks(String userKey){
		List<ProcessTask> result=new ArrayList<ProcessTask>();
		//TODO 暂时查询所有数据
		//List<Task> tasks = taskService.createTaskQuery().taskAssignee(userKey).orderByTaskCreateTime().desc().list();
		List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().desc().list();
		for (Task task : tasks) {
			ProcessTask processTaskBean=new ProcessTask();
			
			String executionId = task.getProcessInstanceId();
			processTaskBean.setExecutionId(executionId);
			
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
			processTaskBean.setProcessName(processDefinition.getName());
			
			processTaskBean.setTaskId(task.getId());
			processTaskBean.setTaskName(task.getName());

			String formKey= formService.getTaskFormData(task.getId()).getFormKey();
			processTaskBean.setFormKey(formKey);
			
			String businessId = getProcessVariable(executionId, FlowConstant.BUSINESS_ID);
			processTaskBean.setBusinessId(businessId);
			
			String businessNo = getProcessVariable(executionId, FlowConstant.BUSINESS_NO);
			processTaskBean.setBusinessNo(businessNo);
			
			String businessTitle = getProcessVariable(executionId, FlowConstant.BUSINESS_TITLE);
			processTaskBean.setBusinessTitle(businessTitle);
			
			result.add(processTaskBean);
		}
		return result;
	}

	/**
	 * 流程变量设置
	 */
	private String getProcessVariable(String executionId,String varName) {
		Object obj=runtimeService.getVariable(executionId,varName);
		return obj==null?"":obj.toString();
	}

}
