package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Palestre;

@Repository
@Transactional(readOnly = true)
public interface PalestreRepository extends JpaRepository<Palestre, Integer>{
	/*
	 * todo: aggiungi cose
	 */
	//ricerca palestre tramite nome
	Optional<Palestre> findByNomePalestra(String nome);
	
	//ricerca palestre tramite posizione
	Optional<Palestre> findByIndirizzoPalestra(String email);
	
}



