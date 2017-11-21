package com.tresct.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DiarioCliente")
public class DiarioCliente {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idDiario;
	
	private String entradaDiario;
	private Timestamp fechaDiario;
	
	@ManyToOne
	@JoinColumn(name="Tramite_idTramite")
	private Tramite tramite;
	
	
	public DiarioCliente() {
	}

	public DiarioCliente(String entradaDiario, Timestamp fechaDiario) {
		
		this.entradaDiario = entradaDiario;
		this.fechaDiario = fechaDiario;
	}

	public int getIdDiario() {
		return idDiario;
	}

	public void setIdDiario(int idDiario) {
		this.idDiario = idDiario;
	}

	public String getEntradaDiario() {
		return entradaDiario;
	}

	public void setEntradaDiario(String entradaDiario) {
		this.entradaDiario = entradaDiario;
	}

	public Timestamp getFechaDiario() {
		return fechaDiario;
	}

	public void setFechaDiario(Timestamp fechaDiario) {
		this.fechaDiario = fechaDiario;
	}
	
	

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	@Override
	public String toString() {
		return "DiarioCliente [idDiario=" + idDiario + ", entradaDiario=" + entradaDiario + ", fechaDiario="
				+ fechaDiario + ", tramite=" + tramite + "]";
	}

}
