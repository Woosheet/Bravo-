package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Corso;



public interface CorsoRepository extends JpaRepository<Corso, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	
	
	//ricerca di tutti i corsi di una determinata palestra
	@Query(
			  value = "SELECT * FROM corsi WHERE id_palestra = :idpalestra ", nativeQuery = true)
	Iterable<Corso> findByPalestra(@Param(value="idpalestra")Integer idpalestra);
	

	//ricerca per nome del corso
	List<Corso> findByNomeCorsoContainingIgnoreCase(String nomeCorso);
	
	
}




