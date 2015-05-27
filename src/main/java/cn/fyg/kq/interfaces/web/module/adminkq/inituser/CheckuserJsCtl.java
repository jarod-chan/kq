package cn.fyg.kq.interfaces.web.module.adminkq.inituser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.application.CheckuserService;
import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserSpecs;
import cn.fyg.kq.domain.shared.kq.Comp;

@Controller
@RequestMapping("checkuser")
public class CheckuserJsCtl {
	
	@Autowired
	CheckuserService checkuserService;
	
	@RequestMapping(value="select.json",method=RequestMethod.GET)
	@ResponseBody 
	public List<Map<String,Object>> simpleQuery(@Param("name")String name){
		Specification<Checkuser> namelike = CheckuserSpecs.nameLike(name);
		Specification<Checkuser> inComp = CheckuserSpecs.inComp(Comp.fangchan);
		Specifications<Checkuser> specs=Specifications.where(inComp).and(namelike);
		Sort sort=new Sort(new Order(Direction.ASC,"user.fnumber"));
		
		List<Checkuser> checkuserList = this.checkuserService.findAll(specs,sort);
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for (Checkuser checkuser : checkuserList) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", checkuser.getFid());
			map.put("name", checkuser.getUser().getFname());
			map.put("kqstatName", checkuser.getKqstat().getName());
			mapList.add(map);
		}
		return mapList;
	}

}
