package cn.fyg.kq.interfaces.web.module.adminsys.rolemenu;

import static cn.fyg.kq.interfaces.web.shared.message.Message.error;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.ModuleService;
import cn.fyg.kq.application.RoleService;
import cn.fyg.kq.domain.model.modmenu.menu.Menu;
import cn.fyg.kq.domain.model.modmenu.module.Module;
import cn.fyg.kq.domain.model.modmenu.role.Role;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;

@Controller
@RequestMapping("rolemenu")
public class RolemenuCtl {
	
	private static final String PATH = "rolemenu/";
	private interface Page {
		String LIST = PATH + "list";
		String ADD = PATH + "add";
		String EDIT = PATH + "edit";
		String SET = PATH + "set";
	}
	
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	public String toList(Map<String,Object> map){
		List<Role> roleList = this.roleService.findAll();
		map.put("roleList", roleList);
		return Page.LIST;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toEdit(Map<String,Object> map){
		return Page.ADD;
	}
	
	@RequestMapping(value="saveAdd",method=RequestMethod.POST)
	public String saveAdd(@RequestParam("key")String key,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		Role role=this.roleService.create();
		BindTool.bindRequest(role, request);
		
		boolean exists = this.roleService.exists(key);
		if(exists){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, error("保存失败！角色已经存在"));
			redirectAttributes.addFlashAttribute("role", role);
			return "redirect:add";
		}
		
		this.roleService.save(role);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}

	@RequestMapping(value="{key}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("key")String key,Map<String,Object> map){
		Role role=this.roleService.find(key);
		map.put("role", role);
		return Page.EDIT;
	}
	
	@RequestMapping(value="saveEdit",method=RequestMethod.POST)
	public String saveEdit(@RequestParam("key")String key,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		Role role=this.roleService.find(key);
		BindTool.bindRequest(role, request);
		this.roleService.save(role);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value="{key}/set",method=RequestMethod.GET)
	public String toSet(@PathVariable("key")String key,Map<String,Object> map){
		Role role=this.roleService.find(key);
		map.put("role", role);
		
		List<Menu> roleMenus = role.getMenus();
		Set<String> roleMenuSn = new HashSet<String>();
		for(Menu menu:roleMenus){
			roleMenuSn.add(menu.getSn());
		}
		map.put("roleMenuSn", roleMenuSn);
		
		Sort sort=new Sort(Direction.ASC,"sn");
		List<Module> moduleList = this.moduleService.findAll(sort);
		map.put("moduleList", moduleList);
		
		return Page.SET;
	}
	
	@RequestMapping(value="saveSet",method=RequestMethod.POST)
	public String saveSet(@RequestParam("key")String key,String[] sn,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		Role role=this.roleService.find(key);
		List<Menu> menus = new ArrayList<Menu>();
		for (String sn_item : sn) {
			Menu menu=new Menu();
			menu.setSn(sn_item);
			menus.add(menu);
		}
		role.setMenus(menus);
		
		this.roleService.save(role);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String deleteSet(@RequestParam("key")String key,RedirectAttributes redirectAttributes){
		this.roleService.delete(key);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("删除成功"));
		return "redirect:list";
	}
	
	
	
	

}
