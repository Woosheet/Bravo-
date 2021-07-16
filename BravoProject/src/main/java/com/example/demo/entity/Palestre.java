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

@SequenceGenerator(name = "seqPal", initialValue = 1, allocationSize = 100)
@Entity // This tells Hibernate to make a table out of this class
public class Palestre {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPal")
	private int ID_Palestra;

	private String nomePalestra;

	private String posizionePalestra;

	private String email;

	private String password;

	private String numTelefono;

	private String info;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private Set<Corsi> ID_Corso;

	public String getNomePalestra() {
		return nomePalestra;
	}

	public void setNomePalestra(String nomePalestra) {
		this.nomePalestra = nomePalestra;
	}

	public String getPosizionePalestra() {
		return posizionePalestra;
	}

	public void setPosizionePalestra(String posizionePalestra) {
		this.posizionePalestra = posizionePalestra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getID_Palestra() {
		return ID_Palestra;
	}

}