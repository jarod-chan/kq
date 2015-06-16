package cn.fyg.kq.interfaces.web.module.adminkq.inituser;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

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
import cn.fyg.kq.domain.model.checkuser.Kqstat;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;


@Controller
@RequestMapping("checkuser")
public class CheckuserCtl {
	
	private static final String PATH = "checkuser/";
	private interface Page {
		String LIST = PATH + "list";
		String SET = PATH + "set";
	}
	
	@Autowired
	UserService userService;
	@Autowired
	CheckuserService checkuserService;
	@Autowired
	SessionUtil sessionUtil;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Specification<Checkuser> inComp = CheckuserSpecs.inComp(user.getAdmincomp());
		Specifications<Checkuser> specs=Specifications.where(inComp);
		Sort sort=new Sort(Direction.DESC,"id");
		List<Checkuser> checkuserList = this.checkuserService.findAll(specs,sort);
		map.put("checkuserList", checkuserList);
		return Page.LIST;
	}
		
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="{checkuserId}/set",method=RequestMethod.GET)
	public String toSet(@PathVariable("checkuserId")Long checkuserId,Map<String,Object> map){
		Checkuser checkuser = this.checkuserService.find(checkuserId);
		map.put("checkuser", checkuser);
		map.put("kqstatVs",Kqstat.values());

		return Page.SET;
	}
	

	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@RequestParam("id")Long checkuserId,HttpServletRequest request,RedirectAttributes redirectAttributes){
		Checkuser checkuser = this.checkuserService.find(checkuserId);
		BindTool.bindRequest(checkuser, request);
		this.checkuserService.save(checkuser);
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, info("保存成功！"));
		return "redirect:list";
	}
		
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String delete(@RequestParam("checkuserFid") Long checkuserFid){
		this.checkuserService.delete(checkuserFid);
		return "redirect:list";
	}

}
