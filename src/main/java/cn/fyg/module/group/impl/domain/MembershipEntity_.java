package cn.fyg.module.group.impl.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MembershipEntity.class)
public abstract class MembershipEntity_ {

	public static volatile SingularAttribute<MembershipEntity, Long> id;
	public static volatile SingularAttribute<MembershipEntity, String> userKey;
	public static volatile SingularAttribute<MembershipEntity, String> groupKey;
	public static volatile SingularAttribute<MembershipEntity, String> code;

}

