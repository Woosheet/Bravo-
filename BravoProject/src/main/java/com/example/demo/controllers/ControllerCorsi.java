package com.example.demo.controllers;

import javax.management.AttributeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<Object> addCorso(@RequestParam String nomeCorso, @RequestParam int IDPalestra) throws AttributeNotFoundException {
	
		//creo un oggetto palestra
		Palestre p = palestreRepository.findById(IDPalestra)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDPalestra));

		// creo un oggetto corso
		Corsi n = new Corsi();

		n.setNomeCorso(nomeCorso);
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


	// update dei dati
	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateUser(@RequestParam Integer id, @RequestParam(required= false) String nomeCorso, int IDPalestra)
			throws AttributeNotFoundException {

		//creo un oggetto palestra
		Palestre p = palestreRepository.findById(IDPalestra)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDPalestra));

		Corsi corso = corsiRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + id));

		corso.setNomeCorso(nomeCorso);
		corso.setPalestra(p);

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
