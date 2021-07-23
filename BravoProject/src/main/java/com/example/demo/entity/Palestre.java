package com.example.demo.entity;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Palestre {
	@Id
	@SequenceGenerator(name = "IndicePalestra", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndicePalestra")
	private int IDPalestra;
	
	private String nomePalestra;
	private String indirizzoPalestra;
	private String numeroTelefono;
	private String info;
	
	//manytoone utenti owner
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDUtente", referencedColumnName = "IDUtente")
	private Utenti utente;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "IDPalestra", referencedColumnName = "IDPalestra")
	private List<Corsi> corsi;

	public Palestre() {
		
	}

	public int getIDPalestra() {
		return IDPalestra;
	}

	public void setID_Palestra(int iDPalestra) {
		IDPalestra = iDPalestra;
	}

	public String getNomePalestra() {
		return nomePalestra;
	}

	public void setNomePalestra(String nomePalestra) {
		this.nomePalestra = nomePalestra;
	}

	public String getIndirizzoPalestra() {
		return indirizzoPalestra;
	}

	public void setIndirizzoPalestra(String indirizzoPalestra) {
		this.indirizzoPalestra = indirizzoPalestra;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Utenti getUtente() {
		return utente;
	}

	public void setUtente(Utenti utente) {
		this.utente = utente;
	}

	public List<Corsi> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corsi> corsi) {
		this.corsi = corsi;
	}
	
	


}