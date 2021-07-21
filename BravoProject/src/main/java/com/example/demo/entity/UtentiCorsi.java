package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
//@Table(name="utenti_corsi")
public class UtentiCorsi {

	public UtentiCorsi() {
		
	}

	@Id
	@SequenceGenerator(name = "IndiceUtentiCorsi",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceUtentiCorsi")
	private int IDUtentiCorsi;
	
	public int getIDUtentiCorsi() {
		return IDUtentiCorsi;
	}

	public void setIDUtentiCorsi(int iDUtentiCorsi) {
		IDUtentiCorsi = iDUtentiCorsi;
	}

	//many to one
	@Column(name = "ID_Utente")
	private Integer IDUtente;
	
	//many to one
	@Column(name = "ID_Corso")
	   private Integer IDCorso;


	
	/*
	@ManyToOne
	@MapsId("ID_Utente")
	@JoinColumn(name = "ID_Utente")
	private Integer IDUtente;

	@ManyToOne
	@MapsId("ID_Corso")
	@JoinColumn(name = "ID_Corso")
	private Integer IDCorso;
	 */
	public Integer getIDUtente() {
		return IDUtente;
	}

	public void setIDUtente(Integer iDUtente) {
		IDUtente = iDUtente;
	}

	public Integer getIDCorso() {
		return IDCorso;
	}

	public void setIDCorso(Integer iDCorso) {
		IDCorso = iDCorso;
	}

	
}
