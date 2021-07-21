package com.example.demo.entity;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@SequenceGenerator(name = "seqAbbonamenti", initialValue = 1, allocationSize = 1)
@Entity // This tells Hibernate to make a table out of this class
public class Abbonamenti {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAbbonamenti")
	private int ID_Abbonamento;

	private Boolean mensile;
	private Boolean semestrale;
	private Boolean annuale;

	private LocalDateTime dataInizio;
	
	private LocalDateTime dataFine;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Utente", referencedColumnName = "ID_Utente")
    private Utenti utente;
	
	

	public Boolean getMensile() {
		return mensile;
	}

	public void setMensile(Boolean mensile) {
		this.mensile = mensile;
	}

	public Boolean getSemestrale() {
		return semestrale;
	}

	public void setSemestrale(Boolean semestrale) {
		this.semestrale = semestrale;
	}

	public Boolean getAnnuale() {
		return annuale;
	}

	public void setAnnuale(Boolean annuale) {
		this.annuale = annuale;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public int getID_Abbonamento() {
		return ID_Abbonamento;
	}
	


}