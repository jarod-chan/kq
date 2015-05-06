package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fyg.kq.application.TagService;
import cn.fyg.kq.domain.model.kq.tag.Tag;
import cn.fyg.kq.domain.model.kq.tag.TagRepository;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagRepository tagRepository;
	
	@Override
	public List<Tag> findByTagtype(String tagtype) {
		return this.tagRepository.findByTagtype(tagtype);
	}

}
