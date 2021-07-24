package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	Optional<Utente> findByEmail(String email);


	@Query("SELECT u FROM Utenti u WHERE u.email = ?1")
	Utente findEmail (String email);
}
