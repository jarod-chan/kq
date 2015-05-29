package cn.fyg.kq.interfaces.web.module.process.start;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

@Controller
@RequestMapping("process/start")
public class StartCtl {
	
	public static final Logger logger=LoggerFactory.getLogger(StartCtl.class);
	
	private static final String PATH = "process/start/";
	private interface Page {
		String START = PATH + "start";
	}
	
	@Autowired
	StartFacade startFacade;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	IdentityService identityService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<ProcessDefinitionBean> processDefinitionBeans=startFacade.getProcessDefinitionBean();
		map.put("processDefinitionBeans", processDefinitionBeans);
		return Page.START;
	}
	
	@RequestMapping(value = "{key}", method = RequestMethod.POST)
	public String start(@PathVariable("key")String key,RedirectAttributes redirectAttributes){
		try {			
			User user=sessionUtil.getValue("user");
			identityService.setAuthenticatedUserId(user.getFid());
			runtimeService.startProcessInstanceByKey(key);
		} catch (ActivitiException e) {
			logger.error("process start fail by key:[]", key);
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.error("流程[%s]启动失败！",key));
			return "redirect:/process/start";
		}
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("流程[%s]启动。",key));
		return "redirect:/process/start";
	}
	
}
