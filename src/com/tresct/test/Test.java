package com.tresct.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tresct.dto.DiarioCliente;
import com.tresct.dto.Imagen;
import com.tresct.dto.Imagen_;
import com.tresct.dto.Inmueble;
import com.tresct.dto.Inmueble_;
import com.tresct.dto.Tramite;
import com.tresct.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {

		Session sesion = null;
		Transaction tx = null;
		consulta(sesion,tx);

	}

	// Consulttas con criteria: Las imagenes de un inmueble
	public static void consulta(Session sesion, Transaction tx) {

		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);

			// Definir el tipo de entidad que retorna la consulta
			Root<Imagen> root = criteria.from(Imagen.class);

			Join<Imagen, Inmueble> join = root.join(Imagen_.inmuebles);
			// Consulto todas las imagenes del inmueble 1
			//criteria.where(builder.equal(join.get(Inmueble_.idInmueble), 6));
			
			//Consulta con un like en ejemplo (Hace lo mismo pero ahora trae las imagenes que estan asociadas al inmueble 5 pero con la plabra asociada a la url)
			criteria.where(
				builder.and(
					builder.equal(join.get(Inmueble_.idInmueble), 5) , 
					builder.like(root.get(Imagen_.url), "%drive%")
				)
			);

			List<Imagen> results = sesion.createQuery(criteria).getResultList();
			System.out.println("Resultados: " + results.toString());
			System.out.println("Resultados int: " + results.size());
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}

	}

	public static void configurar(Session sesion, Transaction tx) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Timestamp time = new Timestamp(new Date().getTime());

			// Creamos inmuebles
			Inmueble inmueble1 = new Inmueble("terreno", "Morelos #100");
			Inmueble inmueble2 = new Inmueble("casa", "CDMX - Insurgentes Norte #200");

			Imagen imagen1 = new Imagen("www.imagesHack", time);
			Imagen imagen2 = new Imagen("www.drive", time);
			Imagen imagen3 = new Imagen("www.mega", time);

			// Asociamos imagen1 a inmueble 1 y 2
			inmueble1.getImagenes().add(imagen1);
			inmueble1.getImagenes().add(imagen2);

			inmueble2.getImagenes().add(imagen1);
			inmueble2.getImagenes().add(imagen3);

			sesion.save(inmueble1);
			sesion.save(inmueble2);

			tx.commit();

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
		} finally {
			if (sesion != null) {
				sesion.close();
			}
		}
	}

}
