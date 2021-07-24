package com.example.demo.HandleUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.Utente;
import com.example.demo.repository.UtenteRepository;

public class UtenteDetailsService implements UserDetailsService {

	@Autowired
	UtenteRepository utenteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Utente u = utenteRepository.findEmail(email);
		
		if(u == null) {
			throw new UsernameNotFoundException("Email non trovata: " + email);
		}
		
		return new UtenteDetails(u);
	}

}
