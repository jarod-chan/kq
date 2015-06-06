package cn.fyg.kq.interfaces.web.module.process.trace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.interfaces.web.shared.constant.FlowConstant;

@Controller
@RequestMapping("trace")
public class TraceCtl {
	
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	ProcessEngineFactoryBean processEngine;
	
	@RequestMapping(value = "b/{executionId}")
	public void readResource(@PathVariable("executionId") String executionId, HttpServletResponse response)throws Exception {
        int width = 1000;   
        int height = 1000;   
        String s = "你好sfsfsffffffffffffffffffffff";   
        
		  Font font = new Font("Serif", Font.BOLD, 10);   
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
	        Graphics2D g2 = (Graphics2D)bi.getGraphics();   
	        g2.setBackground(Color.WHITE);   
	        g2.clearRect(0, 0, width, height);   
	        g2.setPaint(Color.RED);   
	           
	        FontRenderContext context = g2.getFontRenderContext();   
	        Rectangle2D bounds = font.getStringBounds(s, context);   
	        double x = (width - bounds.getWidth()) / 2;   
	        double y = (height - bounds.getHeight()) / 2;   
	        double ascent = -bounds.getY();   
	        double baseY = y + ascent;   
	           
	        g2.drawString(s, (int)x, (int)baseY);   
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	           
	        ImageIO.write(bi, "jpg",  response.getOutputStream()); 

		
	    // 输出资源内容到相应对象
//	    byte[] b = new byte[1024];
//	    int len;
//	    while ((len = imageStream.read(b, 0, 1024)) != -1) {
//	      response.getOutputStream().write(b, 0, len);
//	    }
	}
	
	
	@RequestMapping(value = "{executionId}")
	public void readResource_bak2(@PathVariable("executionId") String executionId, HttpServletResponse response)throws Exception {
		 //TODO 并行节点出现问题
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
		
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
		
		 Thread thread = Thread.currentThread();
		    ClassLoader ccl = thread.getContextClassLoader(); // PUSH
		    try {
		        thread.setContextClassLoader(ClassLoader.getSystemClassLoader());
		        java.awt.Toolkit.getDefaultToolkit().createImage(new byte[]{});
		    } finally {
		        thread.setContextClassLoader(ccl); // POP
		    }

//		 使用spring注入引擎请使用下面的这行代码
//		Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
//		
		ProcessEngineConfigurationImpl processEngineConfiguration = processEngine.getProcessEngineConfiguration();
//        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl)processEngineConfiguration);
		
		
        
        ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        
        
		InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds
				,Collections.<String>emptyList()
				,processEngine.getProcessEngineConfiguration().getActivityFontName(), 
				processEngine.getProcessEngineConfiguration().getLabelFontName(), 
				null, 1.0);
		
	    // 输出资源内容到相应对象
	    byte[] b = new byte[1024];
	    int len;
	    while ((len = imageStream.read(b, 0, 1024)) != -1) {
	      response.getOutputStream().write(b, 0, len);
	    }
	}
	
	@RequestMapping(value = "/bak/{executionId}")
	@ResponseBody
	public void readResource_bak(@PathVariable("executionId") String executionId, HttpServletResponse response)throws Exception {
		 //TODO 并行节点出现问题
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
		
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
		// 使用spring注入引擎请使用下面的这行代码
		Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
		InputStream imageStream =null;// ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
		
		writeStreamToResponse(response, imageStream);
	}
	
	@RequestMapping(value = "{processDefKey}/{businessId}")
	@ResponseBody
	public void trace(@PathVariable("processDefKey") String processDefKey,@PathVariable("businessId") Long businessId,HttpServletResponse response)throws Exception {
		 //TODO 并行节点出现问题
		List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery()
				.processDefinitionKey(processDefKey)
				.variableValueEquals(FlowConstant.BUSINESS_ID, businessId)
				.orderByProcessInstanceId().desc()
				.list();
		if(processInstanceList.isEmpty()){
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);//返回301状态码
			response.setHeader("Location","/pm/404");
			return;
		}
		ProcessInstance processInstance=processInstanceList.get(0);
		InputStream imageStream = getImageStream(processInstance);
		writeStreamToResponse(response, imageStream);
	}

	public InputStream getImageStream(ProcessInstance processInstance) {
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getId());
		
		// 使用spring注入引擎请使用下面的这行代码
		Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
		
		InputStream imageStream =null;// ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
		return imageStream;
	}

	// 输出资源内容到相应对象
	public void writeStreamToResponse(HttpServletResponse response,
			InputStream imageStream) throws IOException {
		 response.setContentType("image/png");
		 OutputStream stream = response.getOutputStream();
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			stream.write(b, 0, len);
		}
        stream.flush();
        stream.close();
        imageStream.close();
	}

}
