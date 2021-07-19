package com.example.demo.registration.palestre;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Palestre;
import com.example.demo.entity.Role;
import com.example.demo.services.PalestreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationServicePalestre {

	private PalestreService palestreService;
	private EmailValidator emailValidator;
	// PalestreRepository palestreRepository;

	public String register(RegistrationRequestPalestre request) {
		// TODO Auto-generated method stub

		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail) {
			throw new IllegalStateException("email not valid");
		}

		return palestreService.signUpPalestre(new Palestre(request.getNomePalestra(), request.getPosizionePalestra(),
				request.getEmail(), request.getPassword(), request.getNumTelefono(), request.getInfo(), Role.USER)

		);
	}
	
	

}
