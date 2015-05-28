package cn.fyg.kq.domain.model.period;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.shared.kq.Comp;

public class PeriodSpecs {

	public static Specification<Period> notFinish() {
		return new Specification<Period>() {
			@Override
			public Predicate toPredicate(Root<Period> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.notEqual(root.get("state"), PeriodState.produce);
			}
		};
	}
	
	public static Specification<Period> isFinish() {
		return new Specification<Period>() {
			@Override
			public Predicate toPredicate(Root<Period> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state"), PeriodState.produce);
			}
		};
	}
	
	public static Specification<Period> inComp(final Comp comp) {
		return new Specification<Period>() {
			@Override
			public Predicate toPredicate(Root<Period> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Comp> get("comp"),comp);
			}
		};
	}

}
