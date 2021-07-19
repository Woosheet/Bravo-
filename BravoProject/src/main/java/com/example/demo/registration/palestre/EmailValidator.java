package com.example.demo.registration.palestre;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{

	@Override
	public boolean test(String t) {
		// TODO regex per la mail
		return true;
	}
	
}
