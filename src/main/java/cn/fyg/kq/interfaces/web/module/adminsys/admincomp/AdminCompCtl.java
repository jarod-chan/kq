package cn.fyg.kq.interfaces.web.module.adminsys.admincomp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.application.AdmincompService;
import cn.fyg.kq.domain.model.kq.admincomp.Admincomp;
import cn.fyg.kq.domain.model.kq.admincomp.AdmincompRepository;


@Controller
@RequestMapping("admincomp")
public class AdminCompCtl {
	
	private static final String PATH = "kq/admincomp/";
	private interface Page {
		String LIST = PATH + "list";
	}
	
	@Autowired
	AdmincompService admincompService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Admincomp> admincompList = this.admincompService.findAll();
		map.put("admincompList", admincompList);
		return Page.LIST;
	}

}
