package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Palestre implements UserDetails {
	@Id
	@SequenceGenerator(name = "seqPal", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPal")
	private int ID_Palestra;

	private String nomePalestra;

	private String posizionePalestra;

	private String email;

	private String password;

	private String numTelefono;

	private String info;
	
	@Enumerated(EnumType.STRING)
	private Role palestraRole;
	
	private Boolean locked;
	
	private Boolean enabled;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private Set<Corsi> ID_Corso;
	
	
	

	public Palestre(String nomePalestra, String posizionePalestra, String email, String password, String numTelefono,
			String info, Role ruolo) {
		super();
		this.nomePalestra = nomePalestra;
		this.posizionePalestra = posizionePalestra;
		this.email = email;
		this.password = password;
		this.numTelefono = numTelefono;
		this.info = info;
		this.palestraRole = ruolo;
		
	}

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(palestraRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nomePalestra;
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

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Role getPalestraRole() {
		return palestraRole;
	}

	public void setPalestraRole(Role palestraRole) {
		this.palestraRole = palestraRole;
	}

}