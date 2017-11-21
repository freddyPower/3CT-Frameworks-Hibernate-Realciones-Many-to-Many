package com.tresct.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Imagen")
public class Imagen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idImagen;
	private String url;
	private Timestamp fecha;
	
	@ManyToMany(mappedBy="")
	private List<Inmueble> inmuebles = new ArrayList<>();
	
	

	public Imagen() {		
	}

	public Imagen(String url, Timestamp fecha) {		
		this.url = url;
		this.fecha = fecha;		
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	@Override
	public String toString() {
		return "Imagen [idImagen=" + idImagen + ", url=" + url + ", fecha=" + fecha + ", inmuebles=" + inmuebles + "]";
	}
	
	

}
