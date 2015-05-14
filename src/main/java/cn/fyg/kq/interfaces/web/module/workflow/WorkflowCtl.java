package cn.fyg.kq.interfaces.web.module.workflow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/workflow/cm")
public class WorkflowCtl {
	
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	WorkflowFacade workflowFacade;
	
	
	@RequestMapping(value = "/process/{ProcessDefinitionId}.png")
	public void processPng(@PathVariable("ProcessDefinitionId")String processDefinitionId,HttpServletResponse response) throws Exception {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		InputStream resourceAsStream= repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 输出跟踪流程信息
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/process/activity/{processInstanceId}.json")
	@ResponseBody
	public Map<String, Object> currentActivity(@PathVariable("processInstanceId")String processInstanceId) throws Exception {
		return workflowFacade.getCurrentActivity(processInstanceId);
	}
	
	
	/**
	 * 读取资源，通过流程ID
	 * @param resourceType			资源类型(xml|image)
	 * @param processInstanceId		流程实例ID	
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/resource/process-instance")
	public void loadByProcessInstance(@RequestParam("type") String resourceType, @RequestParam("pid") String processInstanceId,
			HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = null;
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();

		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = singleResult.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = singleResult.getResourceName();
		}
		resourceAsStream = repositoryService.getResourceAsStream(singleResult.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 生成当前流程图片，高亮当前节点  ，但是动态生成图片中文乱码 
	 * @param processInstanceId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/img/{processInstanceId}")
	public void img(@PathVariable("processInstanceId")String processInstanceId,HttpServletResponse response)throws Exception{
		InputStream resourceAsStream = null;
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processInstance.getProcessDefinitionId());

		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
		
		Object property = PropertyUtils.getProperty(execution, "activityId");
		String activityId = "";
		if (property != null) {
			activityId = property.toString();
		}
		
		List<String> highLightedActivities=new ArrayList<String>();
		highLightedActivities.add(activityId);
		
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		resourceAsStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities);
		
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 输出跟踪流程信息
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/process/trace")
	@ResponseBody
	public List<Map<String, Object>> traceProcess(@RequestParam("pid") String processInstanceId) throws Exception {
		List<Map<String, Object>> activityInfos = workflowFacade.traceProcess(processInstanceId);
		return activityInfos;
	}
	

}
