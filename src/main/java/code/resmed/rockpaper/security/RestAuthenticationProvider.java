package code.resmed.rockpaper.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import code.resmed.rockpaper.model.User;
import code.resmed.rockpaper.repository.UserRepository;

@Component
public class RestAuthenticationProvider implements AuthenticationProvider {
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal().toString();
		final String password = authentication.getCredentials().toString();
		
		User user = userRepo.findByUsername(username);
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Information provided is invalid");
		}
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
		
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
