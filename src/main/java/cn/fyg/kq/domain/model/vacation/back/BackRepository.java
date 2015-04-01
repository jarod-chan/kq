package cn.fyg.kq.domain.model.vacation.back;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.fyg.kq.domain.model.vacation.common.AMPM;
import cn.fyg.module.user.User;

public interface BackRepository extends Repository<Back, Long>{
	
	Back findOne(Long id);
	
	Back save(Back back);
	
	@Query("select a.no from Back a where a.id=" +
			"(select max(b.id) from Back b where b.user=:user )") 
	String findMaxNoByUser(@Param("user")User user);
	
	@Query("from Back a where " +
			"((a.begDayitem.date=:date and a.begDayitem.ampm<=:ampm) or (a.begDayitem.date<:date)) " +
			"and " +
			"((a.endDayitem.date=:date and a.endDayitem.ampm>=:ampm) or (a.endDayitem.date>:date)) ")
	List<Back> findByDayitemContain(@Param("date")Date date,@Param("ampm")AMPM ampm);

}
