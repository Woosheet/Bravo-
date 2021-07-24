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
import com.example.demo.entity.Corso;
import com.example.demo.entity.Palestra;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.PalestraRepository;
import com.example.demo.response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Corsi") // This means URL's start with /demo (after Application path)
public class ControllerCorso {
	@Autowired 
	private CorsoRepository corsoRepository;
	
	@Autowired
	private PalestraRepository palestraRepository;

	// carichiamo un corso
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<Object> addCorso(@RequestParam String nomeCorso, @RequestParam int IDPalestra) throws AttributeNotFoundException {
	
		//creo un oggetto palestra
		Palestra p = palestraRepository.findById(IDPalestra)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDPalestra));

		// creo un oggetto corso
		Corso n = new Corso();

		n.setNomeCorso(nomeCorso);
		n.setPalestra(p);

		// salvo il corso
		return ResponseHandler.generateResponse("Corso aggiunto", HttpStatus.OK, corsoRepository.save(n));
	}

	//metodo inutile
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Corso> getAllCorsi() {
		// This returns a JSON or XML with the users
		return corsoRepository.findAll();
	}

	// ricerca per palestra || i miei corsi
	@GetMapping(path = "/search/palestra")
	public ResponseEntity<Object> findByPalesta(@RequestParam Integer IDPalestra) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsoRepository.findByPalestra(IDPalestra));
	}
	
	//ricerca corso per il nome del corso
	@GetMapping("/search/nomeCorso")
	public ResponseEntity<Object> findByNomeCorso(@RequestParam String nomeCorso) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK, corsoRepository.findByNomeCorsoContainingIgnoreCase(nomeCorso));
	}
	


	// update dei dati
	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateUser(@RequestParam Integer id, @RequestParam(required= false) String nomeCorso, int IDPalestra)
			throws AttributeNotFoundException {

		//creo un oggetto palestra
		Palestra p = palestraRepository.findById(IDPalestra)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDPalestra));

		Corso corso = corsoRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + id));

		corso.setNomeCorso(nomeCorso);
		corso.setPalestra(p);

		corsoRepository.save(corso);
		return ResponseHandler.generateResponse("Corso aggiornato", HttpStatus.OK, corsoRepository.save(corso));

	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<Object> deleteByIdCorso(@RequestParam int id) throws AttributeNotFoundException {
		corsoRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Utente non Esiste: " + id));

		corsoRepository.deleteById(id);

		return ResponseHandler.generateResponse("Corso Eliminato", HttpStatus.OK, null);
	}

}
