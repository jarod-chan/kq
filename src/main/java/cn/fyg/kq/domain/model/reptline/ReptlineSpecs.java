package cn.fyg.kq.domain.model.reptline;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cn.fyg.kq.domain.shared.kq.Comp;

public class ReptlineSpecs {


	public static Specification<Reptline> inComp(final Comp comp) {
		return new Specification<Reptline>() {
			@Override
			public Predicate toPredicate(Root<Reptline> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Comp> get("comp"),comp);
			}
		};
	}

}
