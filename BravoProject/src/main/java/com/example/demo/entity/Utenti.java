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




@SequenceGenerator(name="sequenzaU", initialValue=1, allocationSize=100)
@Entity // This tells Hibernate to make a table out of this class
public class Utenti {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequenzaU")
  private int ID_Utente;


  private String nome;

  private String cognome;
  
  private String email;
  
  private String nomeUtente;
  
  private String password;
  
  @ManyToMany
  @JoinTable(
		  name = "UtentiCorsi", 
		  joinColumns = @JoinColumn(name = "ID_Utente"), 
		  inverseJoinColumns = @JoinColumn(name = "ID_Corso"))
  Set<Corsi> corsiFreq; 
  

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

public int getID_Utente() {
	return ID_Utente;
}

  
  
  

}