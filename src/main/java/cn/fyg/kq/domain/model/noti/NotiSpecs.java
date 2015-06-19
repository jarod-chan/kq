package cn.fyg.kq.domain.model.noti;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cn.fyg.kq.domain.model.user.User;

public class NotiSpecs {
	
	public static Specification<Noti> ofUser(final User user){
		return new Specification<Noti>() {
			@Override
			public Predicate toPredicate(Root<Noti> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("receiver").<String>get("fid"), user.getFid());
			}
		};
	}

}
