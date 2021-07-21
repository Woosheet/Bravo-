package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SequenceGenerator(name = "IndiceCorsi", initialValue = 1, allocationSize = 1)
@Entity // This tells Hibernate to make a table out of this class
public class Corsi {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceCorsi")
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
	

	@ManyToOne( cascade = CascadeType.ALL)
	 @JoinColumn(name = "ID_Palestra", referencedColumnName = "ID_Palestra")
   	private Palestre palestra;

   
	  
	public Palestre getPalestra() {
		return palestra;
	}


	public void setPalestra(Palestre palestra) {
		this.palestra = palestra;
	}


	@OneToMany(targetEntity=UtentiCorsi.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Corso", referencedColumnName = "ID_Corso")
    private List<UtentiCorsi> utentiCorsi;



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