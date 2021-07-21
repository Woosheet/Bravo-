package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Palestre;
import com.example.demo.entity.Role;
import com.example.demo.repository.PalestreRepository;

import response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Palestre") // This means URL's start with /demo (after Application path)
public class ControllerPalestre {
	@Autowired 
	private PalestreRepository palestreRepository;
	
	
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody ResponseEntity<Object> addNewUser(@RequestParam String nomePalestra, @RequestParam String posizionePalestra,
			@RequestParam String email, @RequestParam String password, @RequestParam String numTelefono,
			@RequestParam String info) {
		
		
	Palestre p = new Palestre(nomePalestra, posizionePalestra,email, password, numTelefono, info);
	
	return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, palestreRepository.save(p));

		
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Palestre> getAllPalestre() {
		// This returns a JSON or XML with the users
		return palestreRepository.findAll();
	}

}
