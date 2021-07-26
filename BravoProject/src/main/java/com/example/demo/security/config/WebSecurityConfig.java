package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.HandleUserDetails.UtenteDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UtenteDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(
				"/Palestra/Registration",
				"/Palestra/add",
				"/Palestra/all", 
				"/Corso/add",
				"/Corso/all", 
				"/Corso/search/palestra",
				"/Corso/update",
				"/Corso/search/attivita",
				"/Corso/search/date",
				"/Corso/search/time",
				"/Corso/delete",
				"/Utente/add",
				"/Utente/all",
				"/Utente/searchUserById/{id}",
				"/partecipazione/addPartecipazione",
				"/partecipazione/deletePartecipazione",
				"/Abbonamento/add",
				"/Utente//registrationProcess"
				).permitAll().anyRequest()
				.authenticated().and().formLogin(); 
	//	http.authorizeRequests().antMatchers("/**").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("email")
		.password("password")
		.roles("USER");
		}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

}
