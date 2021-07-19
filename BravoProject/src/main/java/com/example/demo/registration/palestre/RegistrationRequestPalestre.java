package com.example.demo.registration.palestre;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestPalestre {

	private  String nomePalestra;
	private  String posizionePalestra;
	private  String email;
	private  String password;
	private  String numTelefono;
	private  String info;
	
	
	public String getNomePalestra() {
		return nomePalestra;
	}
	public String getPosizionePalestra() {
		return posizionePalestra;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public String getInfo() {
		return info;
	}
	
	
	
	
	
	
	
	
}
