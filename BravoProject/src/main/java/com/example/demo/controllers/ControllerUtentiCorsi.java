package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;
import javax.management.AttributeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.Corsi;
import com.example.demo.entity.Utenti;
import com.example.demo.entity.UtentiCorsi;
import com.example.demo.repository.CorsiRepository;
import com.example.demo.repository.UtentiLezioniRepository;
import com.example.demo.repository.UtentiRepository;

import response.ResponseHandler;

@Controller
@RequestMapping(path = "/partecipazione")
public class ControllerUtentiCorsi {

	@Autowired
	private UtentiLezioniRepository utentiCorsiRepository;
	
	@Autowired
	private CorsiRepository corsiRepository;
	
	@Autowired
	private UtentiRepository utentiRepository;
	
	
	
	// Registrazione utente X al Corso X + controllo del utente nel caso sia
	@PostMapping(path = "/addPartecipazione")
	public ResponseEntity<Object> addPartecipazione (@RequestParam Integer IDCorso, @RequestParam Integer IDUtente) throws AttributeNotFoundException{
		
		//corso di cui passiamo l'id
		Corsi c = corsiRepository.findById(IDCorso)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDCorso));
		//utente di cui passiamo l'id
		Utenti u = utentiRepository.findById(IDUtente)
				.orElseThrow(() -> new AttributeNotFoundException("Id not found for this id :: " + IDUtente));
		
		if(c.getDisponibilitaMassima() > c.getPartecipanti()) {//c'è ancora spazio, procedo
			
			if(giaRegistrato(c, IDUtente)) {//non mi sono registrato già
				//oggeto da andare a scrivere
				UtentiCorsi uc = new UtentiCorsi();
				uc.setCorso(c);
				uc.setUtente(u);
				
				//aggiorno il numero di partecipanti al corso
				c.setPartecipanti(c.getPartecipanti()+1);
				corsiRepository.save(c);
				
				//salvo la partecipazione
				UtentiCorsi utenteR = utentiCorsiRepository.save(uc);
				return ResponseHandler.generateResponse("Partecipazione aggiunta!", HttpStatus.OK, utenteR);
				
			}else {//avviso che mi sono gia registrato a questo corso
				return ResponseHandler.generateResponse("Registrazione già effettuata", HttpStatus.BAD_REQUEST, null);
			}
			
		}else {//avviso che non ci sono posti liberi
			return ResponseHandler.generateResponse("Limite massimo di utenti raggiunto", HttpStatus.BAD_REQUEST, null);
		}
		

	}
	
	// Controllo se Utente è già registrato al corso
	
	public boolean giaRegistrato(Corsi corso, Integer IDUtente) {

		//lista di partecipazioni al corso passato per parametro
		List<UtentiCorsi> listaPartecipazione = utentiCorsiRepository.findByCorso(corso);
		for (int i = 0; i < listaPartecipazione.size(); i++) {
			if (IDUtente == listaPartecipazione.get(i).getUtente().getID_Utente()) {
				return false; //return false SE c'è corrispondenza
			}
		}
		return true;//return true se l'utente non partecipa a determinato corso
	}
	
	//elimino una partecipazione
	@DeleteMapping(path = "/deletePartecipazione")
	public ResponseEntity<Object> deletePartecipazione(@RequestParam Integer IDUtenteCorso) {
		utentiCorsiRepository.deleteById(IDUtenteCorso);
		return ResponseHandler.generateResponse("La partecipazione è stata eliminata", HttpStatus.OK, null);
	}

}
