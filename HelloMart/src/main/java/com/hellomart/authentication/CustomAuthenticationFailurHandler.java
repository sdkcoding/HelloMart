package com.hellomart.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class CustomAuthenticationFailurHandler implements AuthenticationFailureHandler {
 
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailurHandler.class);
	
	/* 이동할 URL */
	private String defaultFailUrl = "login";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String url = appendParam(exception);
		
		response.sendRedirect(url);
	}

	private String appendParam(AuthenticationException exception) {
		StringBuilder sb = new StringBuilder(defaultFailUrl);
		
		sb
			.append("?")
			.append("fail=").append("true");
		
		return sb.toString();
	}
	
}