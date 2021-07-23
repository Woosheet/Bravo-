package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Corsi;



public interface CorsiRepository extends JpaRepository<Corsi, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	
	
	//ricerca di tutti i corsi di una determinata palestra
	@Query(
			  value = "SELECT * FROM corsi WHERE id_palestra = :idpalestra ", nativeQuery = true)
	Iterable<Corsi> findByPalestra(@Param(value="idpalestra")Integer idpalestra);
	

	//ricerca per nome del corso
	List<Corsi> findByNomeCorsoContainingIgnoreCase(String nomeCorso);
	
	
}




