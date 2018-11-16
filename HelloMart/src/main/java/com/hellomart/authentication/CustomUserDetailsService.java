package com.hellomart.authentication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private AccountService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = service.findAccount(username);
		
		if(account == null) {
			throw new UsernameNotFoundException("Bad Credentials");
		}
		
		String role = account.getRole();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		authorities.add(authority);
		
        UserDetails userDetails = (UserDetails) new User(username, account.getPassword(), authorities);
        
		return userDetails;
	}
	
}
