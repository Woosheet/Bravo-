package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;




@SequenceGenerator(name="sequenzaT", initialValue=1, allocationSize=100)
@Entity // This tells Hibernate to make a table out of this class
public class Trainer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequenzaT")
  private int ID_Trainer;


  private String nome;

  private String cognome;
  
  private String email;
  
  private String nomeUtente;
  
  private String password;
  
  private String informazioni;
  
  @ManyToMany
  @JoinTable(
		  name = "TrainerCorsi", 
		  joinColumns = @JoinColumn(name = "ID_Trainer"), 
		  inverseJoinColumns = @JoinColumn(name = "ID_Corso"))
  Set<Corsi> trainerFreq; 
  

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getNomeUtente() {
	return nomeUtente;
}

public void setNomeUtente(String nomeUtente) {
	this.nomeUtente = nomeUtente;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getID_Trainer() {
	return ID_Trainer;
}

public String getInformazioni() {
	return informazioni;
}

public void setInformazioni(String informazioni) {
	this.informazioni = informazioni;
}



  
  
  

}