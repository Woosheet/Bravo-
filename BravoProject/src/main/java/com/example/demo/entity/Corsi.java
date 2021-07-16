package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
@Entity // This tells Hibernate to make a table out of this class
public class Corsi {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
  private int ID_Corso;

  private String tipo;
  
  private String attivita;
  
  private LocalDate date;
  
  private LocalTime time;
  
  private String stato;
  
  @OneToMany(mappedBy="Palestre")
  private Set<Palestre> ID_Palestra;
  
  
  @ManyToMany(mappedBy = "corsiFreq")
  Set<Utenti> utentiFreq;
  
  @ManyToMany(mappedBy = "trainerFreq")
  Set<Trainer> trainerFreq;



public String getTipo() {
	return tipo;
}


public void setTipo(String tipo) {
	this.tipo = tipo;
}


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


public LocalTime getTime() {
	return time;
}


public void setTime(LocalTime time) {
	this.time = time;
}


public String getStato() {
	return stato;
}


public void setStato(String stato) {
	this.stato = stato;
}


public int getID_Corso() {
	return ID_Corso;
}
  
  
  
  
  
}