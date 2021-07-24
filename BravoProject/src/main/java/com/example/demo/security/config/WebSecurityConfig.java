package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/* 
	@Bean
	public UserDetailsService userDetailsService() {
		return new PalestreService();
	}*/

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(
				"/Palestre/Registration",
				"/Palestre/add",
				"/Palestre/all", 
				"/Corsi/add",
				"/Corsi/all", 
				"/Corsi/search/palestra",
				"/Corsi/update",
				"/Corsi/search/attivita",
				"/Corsi/search/date",
				"/Corsi/search/time",
				"/Corsi/delete",
				"/Utenti/add",
				"/Utenti/all",
				"/Utenti/searchUserById/{id}",
				"/partecipazione/addPartecipazione",
				"/partecipazione/deletePartecipazione",
				"/Abbonamenti/add"
				).permitAll().anyRequest()
				.authenticated().and().formLogin(); 
	//	http.authorizeRequests().antMatchers("/**").permitAll();
	}*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

}
