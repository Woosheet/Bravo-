package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Utenti;

public interface UtentiRepository extends JpaRepository<Utenti, Integer> {

	Optional<Utenti> findByEmail(String email);


	@Query("SELECT u FROM Utenti u WHERE u.email = ?1")
	Utenti findEmail (String email);
}
