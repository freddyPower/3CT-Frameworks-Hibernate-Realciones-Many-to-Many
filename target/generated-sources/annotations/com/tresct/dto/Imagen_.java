package com.tresct.dto;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Imagen.class)
public abstract class Imagen_ {

	public static volatile SingularAttribute<Imagen, Timestamp> fecha;
	public static volatile SingularAttribute<Imagen, Integer> idImagen;
	public static volatile SingularAttribute<Imagen, String> url;
	public static volatile ListAttribute<Imagen, Inmueble> inmuebles;

}

