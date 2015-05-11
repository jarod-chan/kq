package cn.fyg.kq.interfaces.web.kq.kaoqin.kaoqin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;

@Controller
@RequestMapping("kaoqin")
public class KaoqinCtl {
	
	private static final String PATH = "kq/kaoqin/";
	private interface Page {
		String LIST = PATH + "list";
		String EDIT = PATH + "edit";
	}
	
	@Autowired
	KaoqinService kaoqinService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Kaoqin> kaoqinList = this.kaoqinService.findAll();
		map.put("kaoqinList", kaoqinList);
		return Page.LIST;
	}
	
	@RequestMapping(value="{kaoqinId}/edit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("kaoqinId")Long kaoqinId,Map<String,Object> map){
		Kaoqin kaoqin = this.kaoqinService.find(kaoqinId);
		map.put("kaoqin", kaoqin);
		return Page.EDIT;
	}
	

}
