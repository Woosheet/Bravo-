package com.example.demo.controllers;
import java.util.List;

import javax.management.AttributeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Lezione;
import com.example.demo.entity.Utente;
import com.example.demo.entity.UtenteLezione;

import com.example.demo.repository.LezioneRepository;
import com.example.demo.repository.UtenteLezioneRepository;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.response.ResponseHandler;

@Controller
@RequestMapping(path = "/partecipazione")
public class ControllerUtenteLezione {

	@Autowired
	private UtenteLezioneRepository utenteLezioneRepository;//repository partecipazioni
	
	@Autowired
	private LezioneRepository lezioneRepository;//repository lezioni
	
	@Autowired
	private UtenteRepository utenteRepository;//repository utenti
	
	
	
	// Registrazione utente X al Corso X + controllo del utente nel caso sia
	@PostMapping(path = "/addPartecipazione")
	public ResponseEntity<Object> addPartecipazione (@RequestParam Integer IDLezione, @RequestParam Integer IDUtente) throws AttributeNotFoundException{
		
		//corso di cui passiamo l'id
		Lezione l = lezioneRepository.findById(IDLezione)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDLezione));
		//utente di cui passiamo l'id
		Utente u = utenteRepository.findById(IDUtente)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDUtente));
		
		if(l.getDisponibilitaMassima() >  utenteLezioneRepository.countByLezione(l)) {//c'è ancora spazio, procedo
			
			if(giaRegistrato(l, IDUtente)) {//non mi sono registrato già
				
				//oggeto da andare a scrivere
				UtenteLezione ul = new UtenteLezione();
				ul.setLezione(l);
				ul.setUtente(u);
				
				//restituisco la partecipazione dopo averla salvata
				return ResponseHandler.generateResponse("Partecipazione aggiunta!", HttpStatus.OK, utenteLezioneRepository.save(ul));
				
			}else {//avviso che mi sono gia registrato a questa lezione
				return ResponseHandler.generateResponse("Registrazione già effettuata a questa lezione", HttpStatus.BAD_REQUEST, null);
			}
			
		}else {//avviso che non ci sono posti liberi
			return ResponseHandler.generateResponse("Limite massimo di utenti raggiunto per questa lezione", HttpStatus.BAD_REQUEST, null);
		}
		

	}
	
	// Controllo se Utente è già registrato al corso
	
	public boolean giaRegistrato(Lezione lezione, Integer IDUtente) {

		//lista di partecipazioni al corso passato per parametro
		 List<UtenteLezione> listaPartecipazione = utenteLezioneRepository.findByLezione(lezione);
		for (int i = 0; i < listaPartecipazione.size(); i++) {
			if (IDUtente == listaPartecipazione.get(i).getUtente().getIDUtente()) {
				return false; //return false SE c'è corrispondenza
			}
		}
		return true;//return true se l'utente non partecipa a determinato corso
	}
	
	//elimino una partecipazione
	@DeleteMapping(path = "/deletePartecipazione")
	public ResponseEntity<Object> deletePartecipazione(@RequestParam Integer IDUtenteLezione) {
		utenteLezioneRepository.deleteById(IDUtenteLezione);
		return ResponseHandler.generateResponse("La partecipazione è stata eliminata", HttpStatus.OK, null);
	}

}
