package com.example.demo.registration.palestre;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Palestre;
import com.example.demo.entity.Role;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/Palestre/Registration")
@AllArgsConstructor
public class RegistrationControllerPalestre {
	
	private RegistrationServicePalestre registrationServicePalestre;
	
	@PostMapping
	public String register(@RequestBody RegistrationRequestPalestre request) {
		
		
/*	
		return "email: "+request.getEmail()+"\nInfo: "+request.getInfo()+"\nNome: "+request.getNomePalestra()+"\nNumero: "
		+request.getNumTelefono()+"\nPassword "+request.getPassword()+"\nPosizione "+request.getPosizionePalestra();
		
*/
		return registrationServicePalestre.register(request);
	}
	
}
