package com.example.demo.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Lezioni;



public interface LezioniRepository extends JpaRepository<Lezioni, Integer>{

	//ricerca attività per data
	List<Lezioni> findByDataLezione(LocalDate dataL);
	
	//ricerca attività per tempo
	List<Lezioni> findByOrarioLezione(LocalTime orarioL);
	
		
	
}



