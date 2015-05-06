package cn.fyg.kq.domain.model.kq.tag;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface TagRepository extends Repository<Tag,String> {
	
	 List<Tag> findByTagtype(String tagtype);
}
