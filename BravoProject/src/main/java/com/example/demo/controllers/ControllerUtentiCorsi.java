package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;
import javax.management.AttributeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.Corsi;
import com.example.demo.entity.UtentiCorsi;
import com.example.demo.repository.CorsiRepository;
import com.example.demo.repository.UtentiCorsiRepository;

@Controller
@RequestMapping(path = "/partecipazione")
public class ControllerUtentiCorsi {

	@Autowired
	private UtentiCorsiRepository utentiCorsiRepository;
	private CorsiRepository corsiRepository;

	// Registrazione utente X al Corso X + controllo del utente nel caso sia
	// registrato
	@PostMapping(path = "/addPartecipazione")
	public @ResponseBody String addPartecipazione(@RequestParam Integer IDCorso, @RequestParam Integer IDUtente)
			throws AttributeNotFoundException {
		UtentiCorsi uc = new UtentiCorsi();

		Corsi c = corsiRepository.findById(IDCorso)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDCorso));

		if (controlloNumPartecipanti(IDCorso)) {

			if (giaRegistrato(IDCorso, IDUtente)) {
				uc.setIDCorso(IDCorso);
				uc.setIDUtente(IDUtente);
				c.setPartecipanti((c.getPartecipanti() + 1));
				corsiRepository.save(c);
				utentiCorsiRepository.save(uc);
				return "Partecipazione al corso.";
			} else {
				return "Utente già registrato al corso";
			}
		} else {
			return "Massimo numero partecipanti raggiunto";
		}
	}

	// Controllo se Utente è già registrato al corso
	public boolean giaRegistrato(Integer IDCorso, Integer IDUtente) {

		List<UtentiCorsi> listaPartecipazione = utentiCorsiRepository.findByIDCorsi(IDCorso);
		for (int i = 0; i < listaPartecipazione.size(); i++) {
			if (listaPartecipazione.get(i).getIDUtente() == IDUtente) {
				return false;
			}
		}
		return true;
	}

	// Controllo numero partecipanti all'interno di Corso
	public boolean controlloNumPartecipanti(Integer IDCorso) throws AttributeNotFoundException {
		Corsi c = corsiRepository.findById(IDCorso)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDCorso));
		if (c.getPartecipanti() == c.getDisponibilitaMassima()) {
			return false;
		} else {
			return true;
		}
	}

	@DeleteMapping(path = "/deletePartecipazione")
	public @ResponseBody String deletePartecipazione(@RequestParam Integer IDUtenteCorso) {
		utentiCorsiRepository.deleteById(IDUtenteCorso);
		return "Partecipazione al corso eliminata";
	}

}
