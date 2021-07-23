package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;


import javax.persistence.CascadeType;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity // This tells Hibernate to make a table out of this class
public class Lezioni {
	@Id
	@SequenceGenerator(name = "IndiceLezioni", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceLezioni")
	private int IDLezione;

	@DateTimeFormat(style = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dataLezione;//data lezione 


	@DateTimeFormat(style = "HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime orarioInizio; //orario lezione
	
	private int disponibilitaMassima; //numero persone che partecipanoMax
	private String modalitaCorso;
	

	//chiave esterna a corso
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "IDCorso", referencedColumnName = "IDCorso")
   	private Corsi corso;

	public Lezioni() {
	
	}

	public int getIDLezione() {
		return IDLezione;
	}

	public void setIDLezione(int iDLezione) {
		IDLezione = iDLezione;
	}

	public LocalDate getDataLezione() {
		return dataLezione;
	}

	public void setDataLezione(LocalDate dataLezione) {
		this.dataLezione = dataLezione;
	}

	public LocalTime getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(LocalTime orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public int getDisponibilitaMassima() {
		return disponibilitaMassima;
	}

	public void setDisponibilitaMassima(int disponibilitaMassima) {
		this.disponibilitaMassima = disponibilitaMassima;
	}

	public String getModalitaCorso() {
		return modalitaCorso;
	}

	public void setModalitaCorso(String modalitaCorso) {
		this.modalitaCorso = modalitaCorso;
	}

	public Corsi getCorso() {
		return corso;
	}

	public void setCorso(Corsi corso) {
		this.corso = corso;
	}

	

}