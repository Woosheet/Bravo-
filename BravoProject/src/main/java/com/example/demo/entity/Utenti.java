package com.example.demo.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;

@SequenceGenerator(name = "sequenzaU", initialValue = 1, allocationSize = 100)
@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Utenti implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenzaU")
	private int ID_Utente;

	private String anagrafica;

	private String email;

	private String nomeUtente;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role utentiRole;
	
	private Boolean locked;
	
	private Boolean enabled;
	

	@ManyToMany
	@JoinTable(name = "UtentiCorsi", joinColumns = @JoinColumn(name = "ID_Utente"), inverseJoinColumns = @JoinColumn(name = "ID_Corso"))
	Set<Corsi> corsiFreq;

	public String getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(String anagrafica) {
		this.anagrafica = anagrafica;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(utentiRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nomeUtente;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}