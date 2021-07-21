package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Abbonamenti;
import com.example.demo.entity.Utenti;
import com.example.demo.repository.AbbonamentiRepository;
import com.example.demo.repository.UtentiRepository;

import response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Abbonamenti") // This means URL's start with /demo (after Application path)
public class ControllerAbbonamenti {
	@Autowired 
	private AbbonamentiRepository abbonamentiRepository;
	
	@Autowired
	private UtentiRepository utentiRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody ResponseEntity<Object> addNewUser(@RequestParam int periodoAbbonamento, @RequestParam int ID_Utente) throws AttributeNotFoundException {
		

		// creo un oggetto abbonamento
		Abbonamenti n = new Abbonamenti();
		n.setDataInizio(LocalDate.now());
		switch(periodoAbbonamento) {
		case 1:
			n.setMensile(true);
			n.setSemestrale(false);
			n.setAnnuale(false);
			n.setDataFine(n.getDataInizio().plusMonths(1));
			break;
		case 2:
			n.setMensile(false);
			n.setSemestrale(true);
			n.setAnnuale(false);
			n.setDataFine(n.getDataInizio().plusMonths(6));
			break;
		case 3:
			n.setMensile(false);
			n.setSemestrale(false);
			n.setAnnuale(true);
			n.setDataFine(n.getDataInizio().plusYears(1));
			break;
		}
		Utenti u = utentiRepository.findById(ID_Utente)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + ID_Utente));
		
		n.setUtente(u);
	
		// salvo il corso
		abbonamentiRepository.save(n);
		return ResponseHandler.generateResponse("Abbonamento Salvato", HttpStatus.OK, abbonamentiRepository.save(n));

	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Abbonamenti> getAllUsers() {
		// This returns a JSON or XML with the users
		return abbonamentiRepository.findAll();
	}
	
	//Abbonamento dell'utente

}