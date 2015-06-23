package cn.fyg.kq.domain.model.exclude;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ExcludeSpecs {
	
	public static Specification<Exclude> ofPeriod(final Long periodId) {
		return new Specification<Exclude>() {
			@Override
			public Predicate toPredicate(Root<Exclude> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("period_id"),periodId);
			}
		};
	}

}
