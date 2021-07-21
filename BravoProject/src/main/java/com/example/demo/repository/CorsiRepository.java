package com.example.demo.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Corsi;



public interface CorsiRepository extends JpaRepository<Corsi, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	
	//ricerca per palestre (i corsi di data palestra)
	@Query(
			  value = "SELECT * FROM corsi WHERE id_palestra = :idpalestra ", nativeQuery = true)
	Iterable<Corsi> findByPalestra(@Param(value="idpalestra")Integer idpalestra);
	
	//ricerca attività per data
	List<Corsi> findByDate(LocalDate date);
	
	//ricerca attività per tempo
	List<Corsi> findByTempo(LocalTime tempo);
	
	//ricerca per nome del corno
	List<Corsi> findByAttivitaContainingIgnoreCase(String attivita);
		
	
}




