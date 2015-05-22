package cn.fyg.kq.interfaces.web.module.system.login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

import com.google.common.base.Supplier;



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
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String toLogin(Map<String,Object> map) {
		return Page.LOGIN;
	}
	
	@RequestMapping(value = "login",method=RequestMethod.POST)
	public String dologin(LoginBean loginBean,RedirectAttributes redirectAttributes) {
		User user = this.userService.findByFnumber(loginBean.getUsername());//TODO 待修改
		boolean loginSucess=dologin(user,loginBean);
		if(loginSucess){
			this.sessionUtil.setValue("user", user); 
			return "redirect:/process/task";
		}else{
			loginBean.setPassword("");
			redirectAttributes.addFlashAttribute("loginBean", loginBean);
			redirectAttributes.addFlashAttribute(AppConstant.MESSAGE_NAME, "用户名或者密码错误！");	
			return "redirect:/login";
		}
	}
	
	private boolean dologin(User user,LoginBean loginBean){
		if(user==null) {
			logger.info("login fail:"+loginBean);
			return false;
		}

		return true;
	}
	
	
	/*private void initContractor(User user) {
		Supplier supplier=spmemberService.getUserSupplier(user);
		sessionUtil.setValue("supplier", supplier);
		Specifications<Contract> spec=Specifications.where(ContractSpecs.withSupplier(supplier));
		List<Contract> supplierContract = contractService.findAll(spec, new Sort(new Order(Direction.ASC,"id")));
		List<Project> projectList=getContractProject(supplierContract);
		if(projectList!=null && !projectList.isEmpty()){
			sessionUtil.setValue("project", projectList.get(0));
		}
	}
	
	private List<Project> getContractProject(List<Contract> supplierContract) {
		List<Project> projectList= new ArrayList<Project>();
		Set<Long> projectIdSet=new HashSet<Long>();
		for (Contract contract : supplierContract) {
			Project project = contract.getProject();
			Long projectId=project.getId();
			if(!projectIdSet.contains(projectId)){
				projectList.add(project);
				projectIdSet.add(projectId);
			}
		}
		return projectList;
	}
	
	private void initCompany(User user) {
		List<Project> projectList=this.pjmemberService.getUserProject(user);
		if(projectList!=null&&!projectList.isEmpty()){
			sessionUtil.setValue("project", projectList.get(0));
		}
	}
	
	//判断用户是否承包人
	private boolean isSupplierUser(User user) {
		return this.spmemberService.isUserAssigned(user);
	}*/
	
	@RequestMapping(value = "logout",method=RequestMethod.POST)
	public String logout(){  
        //SecurityUtils.getSubject().logout();  
        return "redirect:/login";  
    }  
	
	@RequestMapping(value = "redirecthome", method = RequestMethod.GET)
	public String redirecthome(){
		return "redirect:/fm/task";
	}

}
