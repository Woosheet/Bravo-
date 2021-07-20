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
	
	
	@Query(
			  value = "SELECT * FROM corsi WHERE id_palestra = :idpalestra ", nativeQuery = true)
	Iterable<Corsi> findByPalestra(@Param(value="idpalestra")Integer idpalestra);
	
	//ricerca attività per disponibilita
	List<Corsi> findByCheckDisponibilita(boolean disp);
	
	//ricerca attività per data
	List<Corsi> findByDate(LocalDate date);
	
	//ricerca attività per tempo
	List<Corsi> findByTempo(LocalTime tempo);
		
		
	
}




