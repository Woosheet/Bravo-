package com.example.demo.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Corsi;



public interface CorsiRepository extends JpaRepository<Corsi, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	
	//ricerca attività per palestra
	List<Corsi> findByIDPalestra(int ID_Palestra);
	
	//ricerca attività per disponibilita
	List<Corsi> findByCheckDisponibilita(boolean disp);
	
	//ricerca attività per data
	List<Corsi> findByDate(LocalDate date);
	
	//ricerca attività per tempo
	List<Corsi> findByTempo(LocalTime tempo);
		
		
	
}




