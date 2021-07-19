package com.example.demo.registration.palestre;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/Palestre/Registration")
@AllArgsConstructor
public class RegistrationControllerPalestre {
	
	private RegistrationServicePalestre registrationServicePalestre;
	
	public String register(@RequestBody RegistrationRequestPalestre request) {
		return registrationServicePalestre.register(request);
	}
	
}
