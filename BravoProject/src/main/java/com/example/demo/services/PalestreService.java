package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Palestre;
import com.example.demo.repository.PalestreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PalestreService implements UserDetailsService{
	
	private PalestreRepository palestreRepository;
	private final static String PALESTRA_NOT_FOUND_MSG ="Palestra con email %s non trovata";
	
	


	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return palestreRepository.findByEmail(email).orElseThrow(() 
				-> new UsernameNotFoundException(String.format(PALESTRA_NOT_FOUND_MSG, email)));
	}
	
	

}
