package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Abbonamenti;
import com.example.demo.repository.AbbonamentiRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Abbonamenti") // This means URL's start with /demo (after Application path)
public class ControllerAbbonamenti {
	@Autowired // This means to get the bean called corsiRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private AbbonamentiRepository abbonamentiRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam int periodoAbbonamento, @RequestParam int ID_Utente) {
		

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
		n.setID_Utente(ID_Utente);
	
		// salvo il corso
		abbonamentiRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Abbonamenti> getAllUsers() {
		// This returns a JSON or XML with the users
		return abbonamentiRepository.findAll();
	}
	
	//Abbonamento dell'utente

}