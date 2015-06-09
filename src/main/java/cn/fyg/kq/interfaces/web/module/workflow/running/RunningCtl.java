package cn.fyg.kq.interfaces.web.module.workflow.running;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;

@Controller
@RequestMapping("/workflow/running")
public class RunningCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(RunningCtl.class);
	
	private static final String PATH = "workflow/running/";
	private interface Page {
		String RUNNING = PATH + "running";
	}
	
	@Autowired
	RuntimeService runtimeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
		map.put("processInstances", processInstances);
		return Page.RUNNING;
	}
	
	@RequestMapping(value = "{processInstanceId}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("processInstanceId") String processInstanceId,RedirectAttributes redirectAttributes){
		this.runtimeService.deleteProcessInstance(processInstanceId, null);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("流程[%s]删除成功。",processInstanceId));
		return "redirect:/workflow/running";
	}
	

}
