package com.example.demo.controllers;

import java.util.Optional;

import javax.management.AttributeNotFoundException;

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
import com.example.demo.entity.Utenti;
import com.example.demo.repository.PalestreRepository;
import com.example.demo.repository.UtentiRepository;

import response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Palestre") // This means URL's start with /demo (after Application path)
public class ControllerPalestre {

	@Autowired
	private PalestreRepository palestreRepository;// repository palestre
	@Autowired
	private UtentiRepository uR;// repositori utenti

	// aggiunta di una nuova palestra
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<Object> addNewPalestra(@RequestParam String nomePalestra,
			@RequestParam String indirizzoPalestra, @RequestParam String numeroTelefono, @RequestParam String info,
			@RequestParam int IDOwner) throws AttributeNotFoundException {

		// creo un oggetto palestre
		Palestre p = new Palestre();
		// setto i campi del nuovo oggetto
		p.setNomePalestra(nomePalestra);
		p.setIndirizzoPalestra(indirizzoPalestra);
		p.setNumeroTelefono(numeroTelefono);
		p.setIndirizzoPalestra(indirizzoPalestra);

		// Nuovo oggetto di tipo Utenti per rappresentare l'owner della palestra
		Utenti u = uR.findById(IDOwner)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDOwner));

		p.setUtente(u);// setto l'owner

		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, palestreRepository.save(p));

	}

	// ritorna tutte le palestre
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Palestre> getAllPalestre() {
		// This returns a JSON or XML with the users
		return palestreRepository.findAll();
	}

	// Ricerca per nome palestra
	@GetMapping(path = "/ricercaNome")
	public ResponseEntity<Object> ricercaPerNome(@RequestParam String nomePalestra) {

		// query di ricerca
		Optional<Palestre> p = palestreRepository.findByNomePalestra(nomePalestra);

		if (p.isPresent()) {// se ho risultati
			return ResponseHandler.generateResponse("Lista palestre:", HttpStatus.OK, p);

		} else {// non ho risultati
			return ResponseHandler.generateResponse("Non ci sono palestre con questo nome:", HttpStatus.BAD_REQUEST,
					null);

		}

	}
	
	// Ricerca per indirizzo palestra
	@GetMapping(path = "/ricercaIndirizzo")
	public ResponseEntity<Object> ricercaPerIndirizzo(@RequestParam String indirizzoPalestra) {

		// query di ricerca
		Optional<Palestre> p = palestreRepository.findByIndirizzoPalestra(indirizzoPalestra);

		if (p.isPresent()) {// se ho risultati
			return ResponseHandler.generateResponse("Lista palestre:", HttpStatus.OK, p);

		} else {// non ho risultati
			return ResponseHandler.generateResponse("Non ci sono palestre a questo indirizzo:", HttpStatus.BAD_REQUEST,
					null);

		}

	}
}
