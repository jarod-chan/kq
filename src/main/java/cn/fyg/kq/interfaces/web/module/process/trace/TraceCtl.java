package cn.fyg.kq.interfaces.web.module.process.trace;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trace")
public class TraceCtl {
	
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	ProcessEngineFactoryBean processEngine;
	
	
	@RequestMapping(value = "{executionId}")
	public void readResource(@PathVariable("executionId") String executionId, HttpServletResponse response)throws Exception {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
		
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
				
		ProcessEngineConfigurationImpl processEngineConfiguration = processEngine.getProcessEngineConfiguration(); 
        ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        
 
		InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", 
				activeActivityIds,
				Collections.<String>emptyList(),
				processEngine.getProcessEngineConfiguration().getActivityFontName(), 
				processEngine.getProcessEngineConfiguration().getLabelFontName(), 
				null, 1.0);
		
	    // 输出资源内容到相应对象
	    byte[] b = new byte[1024];
	    int len;
	    while ((len = imageStream.read(b, 0, 1024)) != -1) {
	      response.getOutputStream().write(b, 0, len);
	    }
	}
}
