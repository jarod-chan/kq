package cn.fyg.kq.domain.model.kaoqin;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinState;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.user.User;

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
	
	public static Specification<Kaoqin> inState(final KaoqinState... states) {
		return new Specification<Kaoqin>() {
			@Override
			public Predicate toPredicate(Root<Kaoqin> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return root.get("state").in((Object[])states);
			}
		};
	}
	
	public static Specification<Kaoqin> notFinish() {
		return inState(KaoqinState.produce,KaoqinState.save,KaoqinState.process);
	}
	
	public static Specification<Kaoqin> isFinish() {
		return inState(KaoqinState.finish,KaoqinState.voided);
	}
	
	public static Specification<Kaoqin> ofUser(final User user){
		return new Specification<Kaoqin>() {
			@Override
			public Predicate toPredicate(Root<Kaoqin> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("user").<String>get("fid"), user.getFid());
			}
		};
	}

}
