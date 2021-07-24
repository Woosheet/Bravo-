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
public class Corso {
	@Id
	@SequenceGenerator(name = "IndiceCorsi", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceCorsi")
	private int IDCorso;


	private String nomeCorso; //Descrizione del corso

	//chiave esterna a palestra
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDPalestra", referencedColumnName = "IDPalestra")
   	private Palestra palestra;

	public Corso() {
	
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

	public Palestra getPalestra() {
		return palestra;
	}

	public void setPalestra(Palestra palestra) {
		this.palestra = palestra;
	}




}