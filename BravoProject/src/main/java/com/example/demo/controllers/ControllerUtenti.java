package com.example.demo.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Role;
import com.example.demo.entity.Utenti;
import com.example.demo.repository.UtentiRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Utenti") // This means URL's start with /demo (after Application path)
public class ControllerUtenti {
	@Autowired // This means to get the bean called corsiRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private UtentiRepository utentiRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam String anagrafica, @RequestParam String email,
			@RequestParam String nomeUtente, @RequestParam String password, @RequestParam(required = false) String numTelefono) {
		Utenti u = new Utenti();
		
		u.setAnagrafica(anagrafica);
		u.setEmail(email);
		u.setNomeUtente(nomeUtente);
		u.setPassword(password);
		u.setNumTelefono(numTelefono);
		u.setUtentiRole(Role.USER);
		u.setEnabled(true);
		u.setLocked(false);
		utentiRepository.save(u);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Utenti> getAllPalestre() {
		// This returns a JSON or XML with the users
		return utentiRepository.findAll();
	}
	
	@GetMapping(path = "/searchUserById/{id}")
	public @ResponseBody Optional<Utenti> ricercaUtente(@RequestParam int id) {
		
		return utentiRepository.findById(id);
	}
	
	
	//@PutMapping(path = "/disableAccount")
	
	
	
}
