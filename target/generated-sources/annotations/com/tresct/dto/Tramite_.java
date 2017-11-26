package com.tresct.dto;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tramite.class)
public abstract class Tramite_ {

	public static volatile SingularAttribute<Tramite, Integer> idTramite;
	public static volatile SingularAttribute<Tramite, Timestamp> fechaTramite;
	public static volatile SetAttribute<Tramite, DiarioCliente> diarioClienteSet;
	public static volatile SingularAttribute<Tramite, Avaluo> avaluo;
	public static volatile SingularAttribute<Tramite, String> tipoTramite;

}

