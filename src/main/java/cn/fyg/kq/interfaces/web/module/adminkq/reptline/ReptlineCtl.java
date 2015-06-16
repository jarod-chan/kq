package cn.fyg.kq.interfaces.web.module.adminkq.reptline;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.domain.model.reptline.ReptlineSpecs;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;


@Controller
@RequestMapping("reptline")
public class ReptlineCtl {
	
	private static final String PATH = "reptline/";
	private interface Page {
		String LIST = PATH + "list";
		String ADD = PATH + "add";
	}
	
	@Autowired 
	ReptlineService reptlineService;
	@Autowired 
	SessionUtil sessionUtil;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Specification<Reptline> inComp = ReptlineSpecs.inComp(user.getAdmincomp());
		Sort sort = new Sort(new Order(Direction.ASC,"code"));
		List<Reptline> reptlineList = this.reptlineService.findAll(inComp,sort);
		map.put("reptlineList", reptlineList);
		return Page.LIST;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		map.put("comp", user.getAdmincomp());
		return Page.ADD;
	}
	

	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Reptline reptline,RedirectAttributes redirectAttributes){
		User user=sessionUtil.getValue("user");
		reptline.setComp(user.getAdmincomp());
		String code = reptline.getCode();
		int level=StringUtils.split(code,".").length;
		reptline.setLevel(level);
		
		this.reptlineService.save(reptline);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}


	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("reptlineId") Long reptlineId){
		this.reptlineService.delete(reptlineId);
		return "redirect:list";
	}
}
