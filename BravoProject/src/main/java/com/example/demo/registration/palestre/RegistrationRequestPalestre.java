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
	private  String numeroTelefono;
	private  String info;
	
}
