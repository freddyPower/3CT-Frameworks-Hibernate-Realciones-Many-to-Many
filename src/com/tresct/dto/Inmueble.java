package com.tresct.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Inmueble")
public class Inmueble {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInmueble;
	private String tipo;
	private String domicilio;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable( name="InmuebleImgen" ,
				joinColumns = {@JoinColumn(name="idInmueble")},
				inverseJoinColumns = {@JoinColumn(name="idImagen")})
			
	private List<Imagen> imagenes = new ArrayList<>();

	public Inmueble(String tipo, String domicilio) {		
		this.tipo = tipo;
		this.domicilio = domicilio;		
	}

	public Inmueble() {
	}

	public int getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	@Override
	public String toString() {
		return "Inmueble [idInmueble=" + idInmueble + ", tipo=" + tipo + ", domicilio=" + domicilio + ", imagenes="
				+ imagenes + "]";
	}
	
	
}
