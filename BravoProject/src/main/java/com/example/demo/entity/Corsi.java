package com.example.demo.entity;

import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;


@Entity // This tells Hibernate to make a table out of this class
public class Corsi {
	@Id
	@SequenceGenerator(name = "IndiceCorsi", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceCorsi")
	private int IDCorso;


	private String nomeCorso; //Descrizione del corso
/*
	@DateTimeFormat(style = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate date;	//dove e quando


	@DateTimeFormat(style = "HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime tempo;
	private int disponibilitaMassima; //numero persone che partecipanoMax
	private int partecipanti; //numero persone che partecipano
*/	
	//chiave esterna a palestra
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDPalestra", referencedColumnName = "IDPalestra")
   	private Palestre palestra;

	public Corsi() {
	
	}

	public int getIDCorso() {
		return IDCorso;
	}

	public void setIDCorso(int iD_Corso) {
		IDCorso = iD_Corso;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public Palestre getPalestra() {
		return palestra;
	}

	public void setPalestra(Palestre palestra) {
		this.palestra = palestra;
	}




}