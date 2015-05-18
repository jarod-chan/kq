package cn.fyg.kq.interfaces.web.module.adminkq.reptline;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.kq.user.User;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;


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
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Reptline> reptlineList = this.reptlineService.findAll();
		map.put("reptlineList", reptlineList);
		return Page.LIST;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		return Page.ADD;
	}
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="queryUser",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryUser(@Param("name")String name){
		List<User> userList = this.userService.findAll();
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", userList);
		ret.put("result", true);
		return ret;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Reptline reptline,RedirectAttributes redirectAttributes){
		reptline.setComp("fangchan");
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
