package cn.fyg.kq.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.fyg.kq.application.TagtypeService;
import cn.fyg.kq.domain.model.kq.tagtype.Tagtype;
import cn.fyg.kq.domain.model.kq.tagtype.TagtypeRepository;

@Service
public class TagtypeServiceImpl implements TagtypeService {
	
	@Autowired
	TagtypeRepository tagtypeRepository;

	@Override
	public List<Tagtype> findAll() {
		return this.tagtypeRepository.findAll(new Sort(Sort.Direction.ASC,"key"));
	}

}
