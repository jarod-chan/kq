package cn.fyg.kq.interfaces.web.module.adminsys.tag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.application.TagService;
import cn.fyg.kq.application.TagtypeService;
import cn.fyg.kq.domain.model.kq.tag.Tag;
import cn.fyg.kq.domain.model.kq.tagtype.Tagtype;


@Controller
@RequestMapping("tagtype")
public class TagtypeCtl {
	
	private static final String PATH = "kq/tagtype/";
	private interface Page {
		String LIST = PATH + "list";
		String TAGS = PATH + "tags";
	}
	
	@Autowired
	TagtypeService tagtypeService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(Map<String,Object> map){
		List<Tagtype> tagtypeList = this.tagtypeService.findAll();
		map.put("tagtypeList", tagtypeList);
		return Page.LIST;
	}
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value="{tagtypeKey}",method=RequestMethod.GET)
	public String toTagtype(@PathVariable("tagtypeKey")String tagtypeKey,Map<String,Object> map){
		List<Tag> tagList = this.tagService.findByTagtype(tagtypeKey);
		map.put("tagList", tagList);
		return Page.TAGS;
	}
}
