package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Utenti{
	@Id
	@SequenceGenerator(name = "IndiceUtenti", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IndiceUtenti")
	private int IDUtente;
	
	private String nome;
	
	private String cognome;
	
	private String email;
	
	private String password;
	
	private String numeroTelefono;
	
	private LocalDate scadenzaAbbonamento;
	
	@Enumerated(EnumType.STRING)
	private Role utentiRole = Role.USER;

	// spostare con entity lezione
	@OneToMany(targetEntity=UtentiLezioni.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDUtente", referencedColumnName = "IDUtente")
    private List<UtentiLezioni> utentiLezioni;
	
	
	

	public Utenti() {
		
	}

	public int getIDUtente() {
		return IDUtente;
	}

	public void setIDUtente(int iDUtente) {
		IDUtente = iDUtente;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public LocalDate getScadenzaAbbonamento() {
		return scadenzaAbbonamento;
	}

	public void setScadenzaAbbonamento(LocalDate scadenzaAbbonamento) {
		this.scadenzaAbbonamento = scadenzaAbbonamento;
	}

	public Role getUtentiRole() {
		return utentiRole;
	}

	public void setUtentiRole(Role utentiRole) {
		this.utentiRole = utentiRole;
	}

	public List<UtentiLezioni> getUtentiLezioni() {
		return utentiLezioni;
	}

	public void setUtentiLezioni(List<UtentiLezioni> utentiLezioni) {
		this.utentiLezioni = utentiLezioni;
	}

	

}