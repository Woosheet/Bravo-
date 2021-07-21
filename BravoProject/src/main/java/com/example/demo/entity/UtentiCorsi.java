package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

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

	@ManyToOne( cascade = CascadeType.ALL)
	 @JoinColumn(name = "ID_Utente", referencedColumnName = "ID_Utente")
	private Utenti utente;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_Corso", referencedColumnName = "ID_Corso")
	   private Corsi corso;

	public Utenti getUtente() {
		return utente;
	}

	public void setUtente(Utenti utente) {
		this.utente = utente;
	}

	public Corsi getCorso() {
		return corso;
	}

	public void setCorso(Corsi corso) {
		this.corso = corso;
	}

}
