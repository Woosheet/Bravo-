package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Palestra;

@Repository
@Transactional(readOnly = true)
public interface PalestraRepository extends JpaRepository<Palestra, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	//ricerca palestre tramite nome
	Optional<Palestra> findByNomePalestra(String nome);
	
	//ricerca palestre tramite posizione
	Optional<Palestra> findByIndirizzoPalestra(String email);
	
}



