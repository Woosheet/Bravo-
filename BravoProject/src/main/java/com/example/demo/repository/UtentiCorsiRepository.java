package com.example.demo.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UtentiCorsi;

public interface UtentiCorsiRepository extends JpaRepository<UtentiCorsi, Integer> {
	
	List<UtentiCorsi> findByIDUtente(Integer IDUtente);
	List<UtentiCorsi> findByIDCorsi(Integer IDCorsi);
	

}
