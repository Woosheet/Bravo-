package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
import com.example.demo.entity.Corso;
import com.example.demo.entity.Lezione;

import com.example.demo.repository.LezioneRepository;
import com.example.demo.response.ResponseHandler;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/Lezione") // This means URL's start with /demo (after Application path)
public class ControllerLezione {
	@Autowired
	private LezioneRepository lezioneRepository;// repository lezioni

	// carichiamo una lezione
	@PostMapping(path = "/add")
	public ResponseEntity<Object> addLezione(@RequestParam Corso corso,
			@RequestParam @DateTimeFormat(style = "yyyy-MM-dd") LocalDate dataLezione,
			@RequestParam LocalTime orarioInizio, @RequestParam Integer disponibilitaMassima,
			@RequestParam String modalitaCorso) throws AttributeNotFoundException {

		// creo un oggetto lezione
		Lezione l = new Lezione();

		l.setDataLezione(dataLezione);
		l.setOrarioInizio(orarioInizio);
		l.setDisponibilitaMassima(disponibilitaMassima);
		l.setModalitaCorso(modalitaCorso);
		l.setCorso(corso);

		// salvo la lezione
		return ResponseHandler.generateResponse("Lezione aggiunto", HttpStatus.OK, lezioneRepository.save(l));
	}

	// metodo inutile
	@GetMapping(path = "/all")
	public @ResponseBody List<Lezione> getAllLezioni() {
		// This returns a JSON or XML with the users
		return lezioneRepository.findAll();
	}

	// ricerca per Orario lezione
	@GetMapping(path = "/search/orario")
	public ResponseEntity<Object> findByOrarioInizio(@RequestParam LocalTime orarioInizio) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK,
				lezioneRepository.findByOrarioInizio(orarioInizio));
	}

	// ricerca per data lezione
	@GetMapping(path = "/search/data")
	public ResponseEntity<Object> findByDataLezione(
			@RequestParam @DateTimeFormat(style = "yyyy-MM-dd") LocalDate dataLezione) {
		return ResponseHandler.generateResponse("Lista attivita:", HttpStatus.OK,
				lezioneRepository.findByDataLezione(dataLezione));
	}

	// update dei dati lezione
	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateLezione(@RequestParam Integer IDLezione,
			@RequestParam(required = false) @DateTimeFormat(style = "yyyy-MM-dd") LocalDate dataLezione,
			@RequestParam(required = false) LocalTime orarioInizio,
			@RequestParam(required = false) Integer disponibilitaMassima,
			@RequestParam(required = false) String modalitaCorso) throws AttributeNotFoundException {

		// creo un oggetto lezione
		Lezione l = lezioneRepository.findById(IDLezione)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDLezione));

		l.setDataLezione(dataLezione);
		l.setDisponibilitaMassima(disponibilitaMassima);
		l.setModalitaCorso(modalitaCorso);
		l.setOrarioInizio(orarioInizio);

		lezioneRepository.save(l);
		return ResponseHandler.generateResponse("Lezione aggiornata", HttpStatus.OK, lezioneRepository.save(l));

	}

	// elimino il corso
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Object> deleteByIdLezione(@RequestParam int IDLezione) throws AttributeNotFoundException {

		lezioneRepository.deleteById(IDLezione);

		return ResponseHandler.generateResponse("Corso Eliminato", HttpStatus.OK, null);
	}

}
