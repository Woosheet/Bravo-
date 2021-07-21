package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Abbonamenti;
import com.example.demo.entity.Palestre;
import com.example.demo.entity.Utenti;



public interface AbbonamentiRepository extends JpaRepository<Abbonamenti, Integer>{
	
	Optional<Abbonamenti> findByUtente(Utenti u);
	
}




