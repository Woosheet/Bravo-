package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Corsi;
import com.example.demo.entity.Palestre;
import com.example.demo.repository.CorsiRepository;
import com.example.demo.repository.PalestreRepository;
import com.example.demo.repository.UtentiRepository;

import response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Corsi") // This means URL's start with /demo (after Application path)
public class ControllerCorsi {
	@Autowired 
	private CorsiRepository corsiRepository;
	
	@Autowired
	private PalestreRepository palestreRepository;

	// carichiamo un corso
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<Object> addCorso(@RequestParam String attivita, @RequestParam LocalTime tempo,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@RequestParam int disponibilitaMassima, @RequestParam int ID_Palestra) throws AttributeNotFoundException {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		//creo un oggetto palestra
		Palestre p = palestreRepository.findById(ID_Palestra)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + ID_Palestra));

		// creo un oggetto corso
		Corsi n = new Corsi();

		n.setAttivita(attivita);
		n.setDate(date);
		n.setTempo(tempo);
		n.setDisponibilitaMassima(disponibilitaMassima);
		n.setPartecipanti(0);
		n.setPalestra(p);

		// salvo il corso
		return ResponseHandler.generateResponse("Corso aggiunto", HttpStatus.OK, corsiRepository.save(n));
	}

	//metodo inutile
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Corsi> getAllCorsi() {
		// This returns a JSON or XML with the users
		return corsiRepository.findAll();
	}

	// ricerca per palestra || i miei corsi
	@GetMapping(path = "/search/palestra")
	public ResponseEntity<Object> findByPalesta(@RequestParam Integer IDPalestra) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsiRepository.findByPalestra(IDPalestra));
	}

	// ricerca per data
	@GetMapping(path = "/search/date")
	public ResponseEntity<Object> findByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsiRepository.findByDate(date));
	}

	// ricerca per tempo
	@GetMapping(path = "/search/time")
	public ResponseEntity<Object> findByTempo(@RequestParam LocalTime time) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsiRepository.findByTempo(time));
	}
	
	//ricerca per nome attivita
	@GetMapping(path = "/search/attivita")
	public ResponseEntity<Object> findByAttivitaContainingIgnoreCase(@RequestParam String attivita){
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsiRepository.findByAttivitaContainingIgnoreCase(attivita));
	}
	
	// update dei dati
	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateUser(@RequestParam Integer id, @RequestParam String attivita,
			@RequestParam LocalTime tempo, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam int disponibilitaMassima)
			throws AttributeNotFoundException {

		Corsi corso = corsiRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + id));

		corso.setAttivita(attivita);
		corso.setDate(date);
		corso.setTempo(tempo);
		corso.setDisponibilitaMassima(disponibilitaMassima);
		corso.setID_Corso(id);

		corsiRepository.save(corso);
		return ResponseHandler.generateResponse("Corso aggiornato", HttpStatus.OK, corsiRepository.save(corso));

	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<Object> deleteByIdCorso(@RequestParam int id) throws AttributeNotFoundException {
		corsiRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Utente non Esiste: " + id));

		corsiRepository.deleteById(id);

		return ResponseHandler.generateResponse("Corso Eliminato", HttpStatus.OK, null);
	}

}
