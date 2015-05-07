package cn.fyg.kq.interfaces.web.kq.kaoqin.kaoqin;

import static cn.fyg.kq.interfaces.web.shared.message.Message.info;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.kq.application.KaoqinService;
import cn.fyg.kq.application.QingjiaService;
import cn.fyg.kq.domain.model.kq.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kq.qingjia.Qingjia;
import cn.fyg.kq.domain.model.kq.qingjia.QingjiaState;
import cn.fyg.kq.domain.model.kq.user.User;
import cn.fyg.kq.interfaces.web.shared.constant.AppConstant;
import cn.fyg.kq.interfaces.web.shared.mvc.BindTool;

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
	
	

}
