package com.example.demo.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Lezione;



public interface LezioneRepository extends JpaRepository<Lezione, Integer>{

	//ricerca attività per data
	List<Lezione> findByDataLezione(LocalDate dataL);
	
	//ricerca attività per tempo
	List<Lezione> findByOrarioInizio(LocalTime orarioL);
	
		
	
}



