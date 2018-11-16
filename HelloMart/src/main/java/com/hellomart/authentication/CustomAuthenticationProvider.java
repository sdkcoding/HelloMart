package com.hellomart.authentication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private CustomUserDetailsService authService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		User user = (User)authService.loadUserByUsername(username);
		
		comparePassword(password, user.getPassword());
		
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	private void comparePassword(String password1, String password2) throws BadCredentialsException {
		if(password1 == null || !password1.equals(password2)) {
			throw new BadCredentialsException("Bad Credentials");
		}
	}
	
}
