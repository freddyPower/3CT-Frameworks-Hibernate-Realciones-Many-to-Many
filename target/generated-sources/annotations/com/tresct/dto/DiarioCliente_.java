package com.tresct.dto;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiarioCliente.class)
public abstract class DiarioCliente_ {

	public static volatile SingularAttribute<DiarioCliente, Tramite> tramite;
	public static volatile SingularAttribute<DiarioCliente, String> entradaDiario;
	public static volatile SingularAttribute<DiarioCliente, Timestamp> fechaDiario;
	public static volatile SingularAttribute<DiarioCliente, Integer> idDiario;

}

