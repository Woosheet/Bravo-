package com.example.demo.entity;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;


@Entity

public class UtenteLezione {


	@Id
	@SequenceGenerator(name = "IndiceUtentiLezioni",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceUtentiLezioni")
	private int IDUtentiLezioni;
	

	//chiave a utente
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDUtente", referencedColumnName = "IDUtente")
	private Utente utente;
	
	//chiave a lezione
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDLezione", referencedColumnName = "IDLezione")
	   private Lezione lezione;

	public UtenteLezione() {
		
	}

	public int getIDUtentiLezioni() {
		return IDUtentiLezioni;
	}

	public void setIDUtentiLezioni(int iDUtentiLezioni) {
		IDUtentiLezioni = iDUtentiLezioni;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Lezione getLezione() {
		return lezione;
	}

	public void setLezione(Lezione lezione) {
		this.lezione = lezione;
	}


	
	
	
}
