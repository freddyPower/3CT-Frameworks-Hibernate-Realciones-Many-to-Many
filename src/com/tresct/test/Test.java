package com.tresct.test;

import java.sql.Timestamp;
import java.util.Date;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tresct.dto.DiarioCliente;
import com.tresct.dto.Imagen;
import com.tresct.dto.Inmueble;
import com.tresct.dto.Tramite;
import com.tresct.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {

		Session sesion = null;
		Transaction tx =  null;

		try {
			
			sesion = HibernateUtil.getSessionFactory().openSession();
			tx = sesion.beginTransaction();
			Timestamp time = new Timestamp(new Date().getTime());
			
			//Creamos inmuebles
			Inmueble inmueble1 = new Inmueble("terreno" , "Morelos #100");
			Inmueble inmueble2 = new Inmueble("casa" , "CDMX - Insurgentes Norte #200");
			
			Imagen imagen1 = new Imagen("www.imagesHack" , time);
			Imagen imagen2 = new Imagen("www.drive" , time);
			Imagen imagen3 = new Imagen("www.mega" , time);
			
			//Asociamos imagen1 a inmueble 1 y 2
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
			sesion.close();
		}

	}

}
