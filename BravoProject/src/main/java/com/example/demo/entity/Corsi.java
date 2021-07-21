package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
@Entity // This tells Hibernate to make a table out of this class
public class Corsi {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private int ID_Corso;


	private String attivita; //Descrizione del corso

	@DateTimeFormat(style = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate date;	//dove e quando


	@DateTimeFormat(style = "HH:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime tempo;
	private int disponibilitaMassima; //numero persone che partecipanoMax
	private int partecipanti; //numero persone che partecipano
	

	//many to one
	@Column(name = "ID_Palestra")
   	private Integer ID_Palestra;

    public Integer getIDPalestra() {
        return ID_Palestra;
    }

    public void setIDPalestra(Integer ID_Palestra) {
        this.ID_Palestra = ID_Palestra;
    }
	  
	  


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

	public int getDisponibilitaMassima() {
		return disponibilitaMassima;
	}


	public void setDisponibilitaMassima(int disponibilitaMasssima) {
		this.disponibilitaMassima = disponibilitaMasssima;
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