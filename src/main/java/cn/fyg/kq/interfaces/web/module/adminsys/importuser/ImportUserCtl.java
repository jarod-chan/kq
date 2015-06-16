package cn.fyg.kq.interfaces.web.module.adminsys.importuser;

import static cn.fyg.kq.interfaces.web.shared.message.Message.error;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.RoleService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserSpecs;
import cn.fyg.kq.domain.model.modmenu.role.Role;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Comp;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;

@Controller
@RequestMapping("importuser")
public class ImportUserCtl {
	
	private static final String PATH = "importuser/";
	private interface Page {
		String LIST = PATH + "list";
		String IMPT = PATH + "impt";
		String EDIT = PATH + "edit";
		String SET = PATH + "set";
	}
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	public String toList(Map<String,Object> map){
		Sort sort=new Sort(Direction.DESC,"createtime");
		List<User> userList = this.userService.findAll(sort);
		map.put("userList", userList);
		return Page.LIST;
	}
	
	@RequestMapping(value="impt",method=RequestMethod.GET)
	public String toImpt(Map<String,Object> map){
		List<Role> roleList = this.roleService.findAll();
		map.put("roleList", roleList);
		map.put("compList", Comp.values());
		return Page.IMPT;
	}
	

	@RequestMapping(value="impt",method=RequestMethod.POST)
	public String impt(@RequestParam("fid")String fid,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		boolean exists = this.userService.exists(fid);
		if(exists){
			String fnumber=request.getParameter("fnumber");
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("保存失败！用户：%s已经导入",fnumber));
			return "redirect:impt";
		}
		
		User user=this.userService.create();
		BindTool.bindRequest(user, request);
		this.userService.save(user);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}
	
	@RequestMapping(value="{fid}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("fid")String fid,Map<String,Object> map){
		User importuser=this.userService.find(fid);
		map.put("importuser", importuser);
		
		List<Role> roleList = this.roleService.findAll();
		map.put("roleList", roleList);
		map.put("compList", Comp.values());
		return Page.EDIT;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@RequestParam("fid")String fid,HttpServletRequest request,RedirectAttributes redirectAttributes){

		User user=this.userService.find(fid);
		BindTool.bindRequest(user, request);
		this.userService.save(user);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="{fid}/set",method=RequestMethod.GET)
	public String toSet(@PathVariable("fid")String fid,Map<String,Object> map){
		User importuser = this.userService.find(fid);
		map.put("importuser", importuser);
		
		Specification<Checkuser> ofUser = CheckuserSpecs.ofUser(importuser);
		List<Checkuser> checkuserList = this.checkuserService.findAll(Specifications.where(ofUser));
		map.put("checkuserList", checkuserList);
		
		Comp[] compList = Comp.values();
		map.put("compList", compList);
		return Page.SET;
	}
	
	@Autowired
	CheckuserService checkuserService;

	@RequestMapping(value="{fid}/saveSet",method=RequestMethod.POST)
	public String saveSet(@PathVariable("fid")String fid,HttpServletRequest request,RedirectAttributes redirectAttributes){
	
		User user = new User();
		user.setFid(fid);
		
		Checkuser checkuser = this.checkuserService.create(user);
		BindTool.bindRequest(checkuser, request);
		
		Specification<Checkuser> ofUser = CheckuserSpecs.ofUser(checkuser.getUser());
		Specification<Checkuser> inComp = CheckuserSpecs.inComp(checkuser.getComp());
		Specifications<Checkuser> specs=Specifications.where(ofUser).and(inComp);

		List<Checkuser> checkuserList = this.checkuserService.findAll(specs);
		if(!checkuserList.isEmpty()){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("保存失败！用户在当前公司已经初始化"));
			return String.format("redirect:set");
		}
		
		this.checkuserService.save(checkuser);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return String.format("redirect:set");
	}
	
	@RequestMapping(value="{fid}/deleteSet",method=RequestMethod.POST)
	public String deleteSet(@PathVariable("fid")String fid,@RequestParam("checkuserId") Long checkuserId,RedirectAttributes redirectAttributes){
		this.checkuserService.delete(checkuserId);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("删除成功"));
		return "redirect:set";
	}
	
}
