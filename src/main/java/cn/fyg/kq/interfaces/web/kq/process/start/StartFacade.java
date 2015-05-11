package cn.fyg.kq.interfaces.web.kq.process.start;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartFacade {
	
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	FormService formService;
	
	public List<ProcessDefinitionBean> getProcessDefinitionBean(){
		ArrayList<ProcessDefinitionBean> result = new ArrayList<ProcessDefinitionBean>();
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().latestVersion().list();
		for (ProcessDefinition processDefinition : processDefinitions) {
			ProcessDefinitionBean processDefinitionBean = new ProcessDefinitionBean();
			processDefinitionBean.setProcessDefinition(processDefinition);
			String formKey=formService.getStartFormData(processDefinition.getId()).getFormKey(); 
			processDefinitionBean.setFormKey(formKey);
			processDefinitionBean.setIsStartform(StringUtils.isNotBlank(formKey));
			result.add(processDefinitionBean);
		}
		return result;
	}

}
