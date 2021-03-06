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
		//configurar(sesion,tx);
		//consulta(sesion, tx);
		//consultaInmuebles(sesion, tx);
		// actualizar(sesion,tx);
		//eliminarRelacion(sesion, tx);
		//eliminarRelacionCascada(sesion,tx);
		eliminarRelacionTotal(sesion,tx);

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
			// Consulto todas las imagenes del inmueble 6
			// criteria.where(builder.equal(join.get(Inmueble_.idInmueble), 6));

			// Consulta con un like en ejemplo (Hace lo mismo pero ahora trae las imagenes
			// que estan asociadas al inmueble 5 pero con la plabra asociada a la url)
			criteria.where(builder.and(builder.equal(join.get(Inmueble_.idInmueble), 5),
					builder.like(root.get(Imagen_.url), "%drive%")));

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

	// Consulta Inmuebles asociados a imagenes, por lo tanto el elemento root de la
	// consulta por criteria cambia a inmueble
	public static void consultaInmuebles(Session sesion, Transaction tx) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Inmueble> criteria = builder.createQuery(Inmueble.class);

			// Definir el tipo de entidad que retorna la consulta
			Root<Inmueble> root = criteria.from(Inmueble.class);

			Join<Inmueble, Imagen> join = root.join(Inmueble_.imagenes);

			// Realizamos una consulta por criteria para consultar todos los inmuebles
			// asociados a la imagen 7
			// criteria.where(
			// builder.and(
			// builder.equal(join.get(Imagen_.idImagen), 7)
			// )
			// );

			criteria.where(builder.and(builder.equal(join.get(Imagen_.idImagen), 7),
					builder.like(root.get(Inmueble_.tipo), "%terreno%")));

			List<Inmueble> results = sesion.createQuery(criteria).getResultList();
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

	// Este metodo ejemplifica la actualizacion de registros asociados por
	// ManyToMany
	public static void actualizar(Session sesion, Transaction tx) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();

			// Ejemplo: Actualizar imagen7 del inmueble 5
			Inmueble inmueble5 = sesion.load(Inmueble.class, 5);
			Imagen imagen7 = sesion.load(Imagen.class, 7);

			// Primero la elimino de la lista de imagenes
			inmueble5.getImagenes().remove(imagen7);
			imagen7.setUrl("www.nuevoACTUALIZA");
			inmueble5.getImagenes().add(imagen7);
			sesion.save(inmueble5);
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

	// Metodo que demuestra la forma de realizar una eliminacion de una relacion
	// (Eliminar un registro de la tabla intermedia)
	public static void eliminarRelacion(Session sesion, Transaction tx) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();

			// Ejemplo: Eliminar imagen7 del Inmueble5
			Inmueble inmueble5 = sesion.load(Inmueble.class, 5);
			Imagen imagen7 = sesion.load(Imagen.class, 7);

			inmueble5.getImagenes().remove(imagen7);

			sesion.save(inmueble5);
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

	// Metodo que demuestra la forma de eliminar todas las imagenes pertenecientes a un inmueble
	// (Eliminar un registro de la tabla intermedia)
	public static void eliminarRelacionCascada(Session sesion, Transaction tx) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Imagen> criteria = builder.createQuery(Imagen.class);

			// Definir el tipo de entidad que retorna la consulta
			Root<Imagen> root = criteria.from(Imagen.class);
			Join<Imagen, Inmueble> join = root.join(Imagen_.inmuebles);
			
			// Ejemplo: Eliminar todas las imagenes pertenecientes al inmueble7
			criteria.where(builder.equal(join.get(Inmueble_.idInmueble), 7));

			List<Imagen> listaImagenesEliminar = sesion.createQuery(criteria).getResultList();
			Inmueble inmueble7 = sesion.load(Inmueble.class, 7);
			
			for(Imagen imagen: listaImagenesEliminar) {
				inmueble7.getImagenes().remove(imagen);
			}
			sesion.save(inmueble7);
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
	
	// Metodo que demuestra la forma de eliminar todas las imagenes y todos los inmuebles
		
		public static void eliminarRelacionTotal(Session sesion, Transaction tx) {
			try {
				sesion = HibernateUtil.getSessionFactory().openSession();
				tx = sesion.beginTransaction();
				
				Inmueble inmueble9 = sesion.load(Inmueble.class, 9);
				Imagen imagen13 = sesion.load(Imagen.class, 13);
				
				//Esto elimina todas las imagenes del inmueble9 junto con el inmueble9 ;) ojo, esto es inusual
				inmueble9.getImagenes().remove(imagen13);
				
				sesion.delete(inmueble9);
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
