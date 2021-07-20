package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
import com.example.demo.repository.CorsiRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Corsi") // This means URL's start with /demo (after Application path)
public class ControllerCorsi {
	@Autowired // This means to get the bean called corsiRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private CorsiRepository corsiRepository;

	// carichiamo un corso
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addCorso(@RequestParam String attivita, @RequestParam LocalTime tempo,
			@RequestParam LocalDate date, @RequestParam int disponibilitaMassima) {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		// creo un oggetto corso
		Corsi n = new Corsi();

		n.setAttivita(attivita);
		n.setDate(date);
		n.setTempo(tempo);
		n.setDisponibilitaMassima(disponibilitaMassima);
		n.setCheckDisponibilita(true);
		n.setPartecipanti(0);

		// salvo il corso
		corsiRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Corsi> getAllCorsi() {
		// This returns a JSON or XML with the users
		return corsiRepository.findAll();
	}

	// ricerca per palestra
	@GetMapping(path = "/search/palestra")
	public ResponseEntity<Iterable<Corsi>> findByPalesta(@RequestParam Integer IDPalestra) {
		return new ResponseEntity<Iterable<Corsi>>(corsiRepository.findByPalestra(IDPalestra), HttpStatus.OK);
	}

	// ricerca per palestra
	@GetMapping(path = "/search/disponibilita")
	public ResponseEntity<List<Corsi>> findByCheckDisponibilita(@RequestParam boolean disponibilita) {
		return new ResponseEntity<List<Corsi>>(corsiRepository.findByCheckDisponibilita(disponibilita), HttpStatus.OK);
	}

	// ricerca per data
	@GetMapping(path = "/search/date")
	public ResponseEntity<List<Corsi>> findByDate(@RequestParam LocalDate date) {
		return new ResponseEntity<List<Corsi>>(corsiRepository.findByDate(date), HttpStatus.OK);
	}

	// ricerca per tepo
	@GetMapping(path = "/search/time")
	public ResponseEntity<List<Corsi>> findByTempo(@RequestParam LocalTime time) {
		return new ResponseEntity<List<Corsi>>(corsiRepository.findByTempo(time), HttpStatus.OK);
	}
	
	//update dei dati
	@PutMapping(path = "/update")
	public @ResponseBody String updateUser(@RequestParam Integer id,
			@RequestParam(name = "attivita", required = false, defaultValue = "default") String attivita,
			@RequestParam(name = "tempo", required = false, defaultValue = "default") LocalTime tempo,
			@RequestParam(name = "date", required = false, defaultValue = "default") LocalDate date,
			@RequestParam(name = "disponibilitaMassima", required = false, defaultValue = "default") String disponibilitaMassima) {

		Optional<Corsi> corso;
		corso = corsiRepository.findById(id);

		// se l'user esiste
		if (corso.isPresent()) {
			Corsi n = new Corsi();

			// se ho passato un parametro name, lo imposto, altrimenti se non l'ho passato
			// imposto name a quello iniziale
			if (!attivita.equals("default")) {
				n.setAttivita(attivita);
			} else {
				n.setAttivita(corso.get().getAttivita());
			}
			// se ho passato un parametro email, lo imposto, altrimenti se non l'ho passato
			// imposto email a quello iniziale
			if (!tempo.equals("default")) {
				n.setTempo(tempo);
			} else {
				n.setTempo(corso.get().getTempo());
			}
			// se ho passato un parametro eta, lo imposto, altrimenti se non l'ho passato
			// imposto eta a quello iniziale
			if (!date.equals("default")) {
				n.setDate(date);
			} else {
				n.setDate(corso.get().getDate());
			}
			//modifica sta roba della disponibilita massima per piacere
			if (!disponibilitaMassima.equals("default")) {
				n.setDisponibilitaMassima(Integer.parseInt(disponibilitaMassima));
			} else {
				n.setDisponibilitaMassima(corso.get().getDisponibilitaMassima());
			}

			// setto l'id
			n.setID_Corso(id);
			// update
			corsiRepository.save(n);
			return "User Aggiornato.";
		} else {
			return "Non esiste questo user.";
		}

	}
	
	
	@DeleteMapping(path="delete")
	public @ResponseBody String deleteByIdCorso(@RequestParam int id) throws AttributeNotFoundException {
		corsiRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Utente non Esiste: " + id));

		corsiRepository.deleteById(id);

		return "Corso eliminato";
	}
	
}
