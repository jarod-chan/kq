package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.kq.tag.Tag;

public interface TagService {
	
	 List<Tag>  findByTagtype(String tagtype);

}
