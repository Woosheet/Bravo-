package com.example.demo.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.Utente;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UtenteDTO {
	private int IDUtente;
	private String nome;
	private String cognome;
	private String email;
	private String numeroTelefono;
	@DateTimeFormat(style = "yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate scadenzaAboonamento;

	
	
	public UtenteDTO(Utente u) {
	
		IDUtente = u.getIDUtente();
		this.nome = u.getNome();
		this.cognome =u .getCognome();
		this.email = u.getEmail();
		this.numeroTelefono = u.getNumeroTelefono();
		this.scadenzaAboonamento = u.getScadenzaAbbonamento();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public LocalDate getScadenzaAboonamento() {
		return scadenzaAboonamento;
	}

	public void setScadenzaAboonamento(LocalDate scadenzaAboonamento) {
		this.scadenzaAboonamento = scadenzaAboonamento;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

}
