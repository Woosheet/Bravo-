package com.example.demo.repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Corsi;



public interface CorsiRepository extends JpaRepository<Corsi, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	
	//ricerca attività per palestra
	//Iterable<Corsi> findByid_corso_id_palestra(Integer id_corso_id_palestra);
	
	@Query(
			  value = "SELECT * FROM corsi WHERE id_corso_id_palestra = '%id_palestra%' ", nativeQuery = true)
	Iterable<Corsi> findByPalestra(Integer id_palestra);
	
	//ricerca attività per disponibilita
	List<Corsi> findByCheckDisponibilita(boolean disp);
	
	//ricerca attività per data
	List<Corsi> findByDate(LocalDate date);
	
	//ricerca attività per tempo
	List<Corsi> findByTempo(LocalTime tempo);
		
		
	
}




