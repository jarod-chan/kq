package cn.fyg.kq.domain.model.kq.tagtype;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

public interface TagtypeRepository extends Repository<Tagtype,String> {
	
	 List<Tagtype> findAll(Sort sort);
	 
}
