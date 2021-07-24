package HandleUserDetails;

import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.Utenti;
import com.example.demo.repository.UtentiRepository;

public class UtentiDetailsService implements UserDetailsService {

	@Autowired
	UtentiRepository utentiRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Utenti u = utentiRepository.findEmail(email);
		
		if(u == null) {
			throw new UsernameNotFoundException("Email non trovata: " + email);
		}
		
		return new UtentiDetails(u);
	}

}
