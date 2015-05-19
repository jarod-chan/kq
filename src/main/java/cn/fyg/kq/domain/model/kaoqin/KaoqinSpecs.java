package cn.fyg.kq.domain.model.kaoqin;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cn.fyg.kq.domain.model.period.Period;

public class KaoqinSpecs {

	public static Specification<Kaoqin> inPeriod(final Period period) {
		return new Specification<Kaoqin>() {
			@Override
			public Predicate toPredicate(Root<Kaoqin> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(
						cb.equal(root.get("monthitem").get("year"),period.getMonthitem().getYear()),
						cb.equal(root.get("monthitem").get("month"), period.getMonthitem().getMonth()));
			}
		};
	}

}
