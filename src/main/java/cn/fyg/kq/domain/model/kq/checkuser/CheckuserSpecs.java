package cn.fyg.kq.domain.model.kq.checkuser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CheckuserSpecs {

	public static Specification<Checkuser> inComp(final String comp) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("comp"),comp);
			}
		};
	}
	


}
