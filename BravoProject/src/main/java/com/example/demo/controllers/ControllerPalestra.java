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

import com.example.demo.entity.Palestra;
import com.example.demo.entity.Utente;
import com.example.demo.repository.PalestraRepository;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Palestre") // This means URL's start with /demo (after Application path)
public class ControllerPalestra {

	@Autowired
	private PalestraRepository palestraRepository;// repository palestre
	@Autowired
	private UtenteRepository uR;// repositori utenti

	// aggiunta di una nuova palestra
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<Object> addNewPalestra(@RequestParam String nomePalestra,
			@RequestParam String indirizzoPalestra, @RequestParam String numeroTelefono, @RequestParam String info,
			@RequestParam int IDOwner) throws AttributeNotFoundException {

		// creo un oggetto palestre
		Palestra p = new Palestra();
		// setto i campi del nuovo oggetto
		p.setNomePalestra(nomePalestra);
		p.setIndirizzoPalestra(indirizzoPalestra);
		p.setNumeroTelefono(numeroTelefono);
		p.setIndirizzoPalestra(indirizzoPalestra);

		// Nuovo oggetto di tipo Utenti per rappresentare l'owner della palestra
		Utente u = uR.findById(IDOwner)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDOwner));

		p.setUtente(u);// setto l'owner

		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, palestraRepository.save(p));

	}

	// ritorna tutte le palestre
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Palestra> getAllPalestre() {
		// This returns a JSON or XML with the users
		return palestraRepository.findAll();
	}

	// Ricerca per nome palestra
	@GetMapping(path = "/ricercaNome")
	public ResponseEntity<Object> ricercaPerNome(@RequestParam String nomePalestra) {

		// query di ricerca
		Optional<Palestra> p = palestraRepository.findByNomePalestra(nomePalestra);

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
		Optional<Palestra> p = palestraRepository.findByIndirizzoPalestra(indirizzoPalestra);

		if (p.isPresent()) {// se ho risultati
			return ResponseHandler.generateResponse("Lista palestre:", HttpStatus.OK, p);

		} else {// non ho risultati
			return ResponseHandler.generateResponse("Non ci sono palestre a questo indirizzo:", HttpStatus.BAD_REQUEST,
					null);

		}

	}
}
