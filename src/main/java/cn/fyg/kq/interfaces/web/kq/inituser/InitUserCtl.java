package cn.fyg.kq.interfaces.web.kq.inituser;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.kq.checkuser.Checkuser;
import cn.fyg.kq.domain.model.kq.checkuser.CheckuserRepository;
import cn.fyg.kq.interfaces.web.modify.User;
import cn.fyg.kq.interfaces.web.modify.http.AdminHelp;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.service.UserinfoService;


@Controller
@RequestMapping("inituser")
public class InitUserCtl {
	
	private static final String PATH = "kq/inituser/";
	private interface Page {
		String LIST = PATH + "list";
		String ADD = PATH + "add";
	}
	
	@Autowired
	CheckuserRepository checkuserRepository;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Checkuser> checkuserList = this.checkuserRepository.findAll();
		map.put("checkuserList", checkuserList);
		return Page.LIST;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		return Page.ADD;
	}
	
	@Autowired
	UserService userService;
	@Autowired
	CheckuserService checkuserService;
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Checkuser checkuser,RedirectAttributes redirectAttributes){
		String comp="fangchan";
		cn.fyg.kq.domain.model.kq.user.User user = checkuser.getUser();
		user.setFid(checkuser.getFid());
		
		user=this.userService.save(user);
		checkuser.setUser(user);
		checkuser.setComp(comp);
		this.checkuserService.save(checkuser);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}
	
	@Autowired
	AdminHelp adminHelp;
	
	@RequestMapping(value="queryEasuser",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryEasuser(@Param("name")String name){
		List<User> userList=adminHelp.query(name);
		if(userList.size()>10){
			userList=userList.subList(0, 10);
		}
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", userList);
		ret.put("result", true);
		return ret;
	}
	
	@Autowired
	UserinfoService userinfoService;
	
	@RequestMapping(value="queryKquser",method=RequestMethod.GET)
	@ResponseBody
	public  Map<String,Object> queryKquser(@Param("name")String name){
		List<Userinfo> userinfoList = this.userinfoService.queryByName(name);
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("data", userinfoList);
		ret.put("result", true);
		return ret;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("checkuserFid") String checkuserFid){
		this.checkuserService.delete(checkuserFid);
		return "redirect:list";
	}

}
