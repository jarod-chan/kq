package cn.fyg.kq.interfaces.web.module.system.login;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.easser.service.LoginService;
import cn.fyg.easser.service.NetException;
import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.ModuleService;
import cn.fyg.kq.application.RoleService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.modmenu.menu.Menu;
import cn.fyg.kq.domain.model.modmenu.module.Module;
import cn.fyg.kq.domain.model.modmenu.role.Role;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;


@Controller
public class LoginCtl {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginCtl.class);
	
	private static final String PATH = "system/login/";
	private interface Page {
		String LOGIN = PATH + "login";
	}
	
	@Autowired
	UserService userService;
	@Autowired
	SessionUtil sessionUtil;
	@Autowired
	CheckuserService checkuserService; 
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String toLogin(Map<String,Object> map) {
		return Page.LOGIN;
	}
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	LoginService LoginService;
	
	@RequestMapping(value = "login",method=RequestMethod.POST)
	public String dologin(LoginBean loginBean,RedirectAttributes redirectAttributes) {

/*		String fid=null;
		boolean isfinish=false;
		try {
			fid = this.LoginService.login(loginBean.getUsername(), loginBean.getPassword());
			isfinish=true;
		} catch (NetException e) {
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, "连接EAS服务器出错！");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, "EAS调用过程出错！");
		}
		if(!isfinish){
			return "redirect:/login";
		}
		if(fid==null){
			logger.info("login fail:"+loginBean);
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, "用户名或者密码错误！");
			return relogin(loginBean, redirectAttributes);
		}
		
		User user = this.userService.find(fid);*/
		
		User user = this.userService.findByFnumber(loginBean.getUsername()); //TODO 待修改
		if(user==null){
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, "用户没有在考勤系统初始化！");
			return relogin(loginBean, redirectAttributes);
		}
		

		initUserSession(user);
		
		return "redirect:/process/task";
	}

	private void initUserSession(User user) {
		sessionUtil.invalidate();
		this.sessionUtil.setValue("user", user); 
		List<Module> moduleList = userModuleMenu(user.getRole().getKey());
		this.sessionUtil.setValue("userModuleList", moduleList);
	}

	/*
	 * 获得用户对应角色拥有的菜单权限
	 */
	private List<Module> userModuleMenu(String roleKey) {
		Role role = this.roleService.find(roleKey, false);
		List<Menu> menus = role.getMenus();
		Set<String> menuSn = new HashSet<String>();
		for(Menu menu:menus){
			menuSn.add(menu.getSn());
		}
		Sort sort=new Sort(Direction.ASC,"sn");
		List<Module> moduleList = this.moduleService.findAll(sort);
		for (Module module : moduleList) {
			Iterator<Menu> iterator = module.getMenus().iterator();
			while(iterator.hasNext()){
				Menu menu = iterator.next();
				if(!menuSn.contains(menu.getSn())){
					iterator.remove();
				}
			}
		}
		Iterator<Module> iterator = moduleList.iterator();
		while(iterator.hasNext()){
			Module module = iterator.next();
			if(module.getMenus().isEmpty()){
				iterator.remove();
			}
		}
		return moduleList;
	}

	private String relogin(LoginBean loginBean,RedirectAttributes redirectAttributes) {
		loginBean.setPassword("");
		redirectAttributes.addFlashAttribute("loginBean", loginBean);
		return "redirect:/login";
	}
	

	
	@RequestMapping(value = "logout",method=RequestMethod.POST)
	public String logout(){  
		sessionUtil.invalidate();
        return "redirect:/login";  
    }  
	
}
