package cn.fyg.kq.application;

import java.util.List;

import cn.fyg.kq.domain.model.opinion.Opinion;

public interface OpinionService {
	
	Opinion append(Opinion opinion);
	
	List<Opinion> listOpinions(String businessCode,Long businessId);
	
	void clear(String businessCode,Long businessId);
}
