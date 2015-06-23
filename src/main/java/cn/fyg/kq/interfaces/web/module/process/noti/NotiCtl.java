package cn.fyg.kq.interfaces.web.module.process.noti;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.application.NotiService;
import cn.fyg.kq.domain.model.noti.Noti;
import cn.fyg.kq.domain.model.noti.NotiSpecs;
import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.interfaces.web.shared.session.SessionUtil;

@Controller
@RequestMapping("noti")
public class NotiCtl {
	
	private static final String PATH = "noti/";
	private interface Page {
		String LIST = PATH + "list";
		String VIEW = PATH + "view";
	}
	
	@Autowired
	NotiService notiService;
	@Autowired
	SessionUtil sessionUtil;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		User user=sessionUtil.getValue("user");
		Specification<Noti> ofUser = NotiSpecs.ofUser(user);
		Specifications<Noti> specs = Specifications.where(ofUser);
		Sort sort=new Sort(Direction.DESC,"createtime");
		List<Noti> notiList=this.notiService.findAll(specs, sort);
		map.put("notiList", notiList);
		return Page.LIST;
	}
	
	@RequestMapping(value="{notiId}/view",method=RequestMethod.GET)
	public String toView(@PathVariable("notiId")Long notiId,Map<String,Object> map){
		this.notiService.read(notiId);
		Noti noti=this.notiService.find(notiId);
		map.put("noti", noti);
		return Page.VIEW;
	}

}
