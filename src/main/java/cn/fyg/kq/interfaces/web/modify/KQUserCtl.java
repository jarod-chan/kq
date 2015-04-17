package cn.fyg.kq.interfaces.web.modify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.kq.application.KQUserService;
import cn.fyg.kq.domain.model.md.KQUser;

@Controller
@RequestMapping("/md")
public class KQUserCtl {

	private static final String PATH = "modify/";
	private interface Page {
		String KQUSER = PATH + "kquser";
	}
	
	@Autowired
	KQUserService kqUserService;
	
	@RequestMapping(value="kquser",method=RequestMethod.GET)
	public String toFisrst(Map<String,Object> map){
		List<KQUser> userList = this.kqUserService.all();
		map.put("userList", userList);
		return Page.KQUSER;
	}
	
	@RequestMapping(value="kquser/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(@Param("fid")String fid){
		Map<String,Object> ret = new HashMap<String,Object>();
		try{
			this.kqUserService.delete(fid);
			ret.put("result", true);
		}catch(Exception e){
			ret.put("result", false);
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

}
