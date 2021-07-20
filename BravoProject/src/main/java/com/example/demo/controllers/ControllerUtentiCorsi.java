package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UtentiCorsi;
import com.example.demo.repository.UtentiCorsiRepository;

@Controller
@RequestMapping(path = "/partecipazione")
public class ControllerUtentiCorsi {

	@Autowired
	private UtentiCorsiRepository utentiCorsiRepository;

	@PostMapping(path = "/addPartecipazione")
	public @ResponseBody String addPartecipazione(@RequestParam Integer IDCorso, @RequestParam Integer IDUtente) {
		UtentiCorsi uc = new UtentiCorsi();
		//Aggiungere controllo numero partecipanti
		
		//Aggiungere l'indice per il controllo
		
		if (giaRegistrato(IDCorso, IDUtente)) {
			uc.setIDCorso(IDCorso);
			uc.setIDUtente(IDUtente);
			utentiCorsiRepository.save(uc);
			return "Partecipazione al corso.";
		} else {
			return "Utente già registrato al corso";
		}
	}

	public boolean giaRegistrato(Integer IDCorso, Integer IDUtente) {
		UtentiCorsi uc = new UtentiCorsi();
		List<UtentiCorsi> listaPartecipazione = utentiCorsiRepository.findByIDCorsi(IDCorso);
		for (int i = 0; i < listaPartecipazione.size(); i++) {
			if (listaPartecipazione.get(i).getIDUtente() == IDUtente) {
				return false;
			}
		}
		return true;
	}
}
