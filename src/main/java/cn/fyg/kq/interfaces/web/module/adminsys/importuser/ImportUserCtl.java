package cn.fyg.kq.interfaces.web.module.adminsys.importuser;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.application.TagService;
import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserSpecs;
import cn.fyg.kq.domain.model.checkuser.Kqstat;
import cn.fyg.kq.domain.model.kq.tag.Tag;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.message.Message;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;

@Controller
@RequestMapping("importuser")
public class ImportUserCtl {
	
	private static final String PATH = "importuser/";
	private interface Page {
		String LIST = PATH + "list";
		String EDIT = PATH + "edit";
	}
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="list",method={RequestMethod.GET,RequestMethod.POST})
	public String toList(Map<String,Object> map){
		List<User> userList = this.userService.findAll();
		map.put("userList", userList);
		return Page.LIST;
	}
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		List<Tag> tagList = tagService.findByTagtype("comp");
		map.put("tagList", tagList);
		return Page.EDIT;
	}
	
	@Autowired
	CheckuserService checkuserService;
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@RequestParam("fid")String fid,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		User user=StringUtils.isBlank(fid)?this.userService.create():this.userService.find(fid);
		BindTool.bindRequest(user, request);
		user=this.userService.save(user);
		
		Specification<Checkuser> ofUser = CheckuserSpecs.ofUser(user);
		Specifications<Checkuser> specs=Specifications.where(ofUser);

		List<Checkuser> checkuserList = this.checkuserService.findAll(specs);
		for (Checkuser checkuser : checkuserList) {
			this.checkuserService.delete(checkuser.getId());
		}
		
		String comp = request.getParameter("comp");
		Checkuser checkuser = new Checkuser();
		checkuser.setKqstat(Kqstat.todo);
		checkuser.setComp(comp);
		checkuser.setUser(user);
		this.checkuserService.save(checkuser);
		
		redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, Message.info("保存成功"));
		return "redirect:list";
	}
}
