package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Palestre;
import com.example.demo.repository.PalestreRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Palestre") // This means URL's start with /demo (after Application path)
public class ControllerPalestre {
	@Autowired // This means to get the bean called corsiRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private PalestreRepository palestreRepository;
	

	

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam String nomePalestra, @RequestParam String posizionePalestra,
			@RequestParam String email, @RequestParam String password, @RequestParam String numTelefono,
			@RequestParam String info) {
		

		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Palestre> getAllPalestre() {
		// This returns a JSON or XML with the users
		return palestreRepository.findAll();
	}

}