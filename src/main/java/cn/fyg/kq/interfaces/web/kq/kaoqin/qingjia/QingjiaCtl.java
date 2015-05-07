package cn.fyg.kq.interfaces.web.kq.kaoqin.qingjia;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.QingjiaService;
import cn.fyg.kq.domain.model.kq.qingjia.Ampm;
import cn.fyg.kq.domain.model.kq.qingjia.Qingjia;
import cn.fyg.kq.domain.model.kq.qingjia.QingjiaState;
import cn.fyg.kq.domain.model.kq.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;

@Controller
@RequestMapping("qingjia")
public class QingjiaCtl {
	
	private static final String PATH = "kq/qingjia/";
	private interface Page {
		String LIST = PATH + "list";
		String EDIT = PATH + "edit";
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
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("qingjiaId") Long qingjiaId){
		qingjiaService.delete(qingjiaId);
		return "redirect:list";
	}

}
