package cn.fyg.kq.interfaces.web.module.process.start;

import org.activiti.engine.repository.ProcessDefinition;

public class ProcessDefinitionBean {
	
	private ProcessDefinition processDefinition;
	
	private String formKey;
	
	private Boolean isStartform;

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public Boolean getIsStartform() {
		return isStartform;
	}

	public void setIsStartform(Boolean isStartform) {
		this.isStartform = isStartform;
	}
	
	

}
