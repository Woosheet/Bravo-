package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
@Entity // This tells Hibernate to make a table out of this class
public class Corsi {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private int ID_Corso;


	private String attivita; //Descrizione del corso

	private LocalDate date;	//dove e quando
	private LocalTime tempo;

	private boolean checkDisponibilita;
	private int disponibilitaMasssima; //numero persone che partecipanoMax
	private int partecipanti; //numero persone che partecipano
	

	@ManyToMany(mappedBy = "corsiFreq")
	Set<Utenti> utentiFreq;


	public String getAttivita() {
		return attivita;
	}


	public void setAttivita(String attivita) {
		this.attivita = attivita;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getTempo() {
		return tempo;
	}


	public void setTempo(LocalTime tempo) {
		this.tempo = tempo;
	}



	public Set<Utenti> getUtentiFreq() {
		return utentiFreq;
	}


	public void setUtentiFreq(Set<Utenti> utentiFreq) {
		this.utentiFreq = utentiFreq;
	}


	public int getID_Corso() {
		return ID_Corso;
	}


	public boolean isCheckDisponibilita() {
		return checkDisponibilita;
	}


	public void setCheckDisponibilita(boolean checkDisponibilita) {
		this.checkDisponibilita = checkDisponibilita;
	}


	public int getDisponibilitaMasssima() {
		return disponibilitaMasssima;
	}


	public void setDisponibilitaMasssima(int disponibilitaMasssima) {
		this.disponibilitaMasssima = disponibilitaMasssima;
	}


	public int getPartecipanti() {
		return partecipanti;
	}


	public void setPartecipanti(int partecipanti) {
		this.partecipanti = partecipanti;
	}


	public void setID_Corso(int iD_Corso) {
		ID_Corso = iD_Corso;
	}


	


	

}