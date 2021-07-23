package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Utenti;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

	Optional<Utenti> findByEmail(String email);

	
}
