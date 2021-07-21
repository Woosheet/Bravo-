package com.example.demo.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Corsi;
import com.example.demo.entity.Utenti;
import com.example.demo.entity.UtentiCorsi;

public interface UtentiCorsiRepository extends JpaRepository<UtentiCorsi, Integer> {
	
//	List<UtentiCorsi> findByUtente(Utenti utente);
	
  //lista di corsi dove il campo corso equivale al corso passato per parametro
  List<UtentiCorsi> findByCorso(Corsi corso);
	

}
