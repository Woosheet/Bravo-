package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lezione;
import com.example.demo.entity.UtenteLezione;

public interface UtenteLezioneRepository extends JpaRepository<UtenteLezione, Integer> {
	
	//List<UtentiCorsi> findByUtente(Utenti utente);
	
  //lista di partecipazione dove il campo lezione equivale alla lezione passat1 per parametro
  List<UtenteLezione> findByLezione(Lezione lezione);
  
  int countByLezione(Lezione lezione);
	

}
