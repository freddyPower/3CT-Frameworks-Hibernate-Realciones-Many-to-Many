package com.tresct.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inmueble.class)
public abstract class Inmueble_ {

	public static volatile SingularAttribute<Inmueble, Integer> idInmueble;
	public static volatile SingularAttribute<Inmueble, String> tipo;
	public static volatile ListAttribute<Inmueble, Imagen> imagenes;
	public static volatile SingularAttribute<Inmueble, String> domicilio;

}

