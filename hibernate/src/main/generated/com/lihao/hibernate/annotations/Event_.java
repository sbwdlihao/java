package com.lihao.hibernate.annotations;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, Date> date;
	public static volatile SingularAttribute<Event, Long> id;
	public static volatile SingularAttribute<Event, String> title;

}

