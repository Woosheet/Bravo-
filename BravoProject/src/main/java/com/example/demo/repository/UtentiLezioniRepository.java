package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lezioni;
import com.example.demo.entity.UtentiLezioni;

public interface UtentiLezioniRepository extends JpaRepository<UtentiLezioni, Integer> {
	
	//List<UtentiCorsi> findByUtente(Utenti utente);
	
  //lista di partecipazione dove il campo lezione equivale alla lezione passat1 per parametro
  List<UtentiLezioni> findByLezione(Lezioni lezione);
	

}
