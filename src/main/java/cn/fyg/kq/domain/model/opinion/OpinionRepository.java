package cn.fyg.kq.domain.model.opinion;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface OpinionRepository extends Repository<Opinion, Long> {

	Opinion save(Opinion opinion);
	
	Opinion findOne(Long id);
	
	List<Opinion> findByBusinessCodeAndBusinessIdOrderByIdAsc(String businessCode,Long businessId);
	
	@Modifying 
	@Query("delete from Opinion a where a.businessCode=:businessCode and a.businessId=:businessId") 
	void deleteByBusinessCodeAndBusinessId(@Param("businessCode")String businessCode,@Param("businessId")Long businessId);
}
