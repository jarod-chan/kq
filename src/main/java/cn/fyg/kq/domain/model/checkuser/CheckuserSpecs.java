package cn.fyg.kq.domain.model.checkuser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cn.fyg.kq.domain.model.user.User;
import cn.fyg.kq.domain.shared.kq.Comp;

public class CheckuserSpecs {
	
	public static Specification<Checkuser> nameLike(final String name) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("user").<String>get("fnumber"),name+"%");
			}
		};
	}

	public static Specification<Checkuser> inComp(final String comp) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("comp"),comp);
			}
		};
	}
	
	public static Specification<Checkuser> inComp(final Comp comp) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Comp> get("comp"),comp);
			}
		};
	}
	
	public static Specification<Checkuser> ofUser(final User User) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("fid"),User.getFid());
			}
		};
	}
	
	public static Specification<Checkuser> isKqstat(final Kqstat kqstat) {
		return new Specification<Checkuser>() {
			@Override
			public Predicate toPredicate(Root<Checkuser> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("kqstat"),kqstat);
			}
		};
	}
	


}
