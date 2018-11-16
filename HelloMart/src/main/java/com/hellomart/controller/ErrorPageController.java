package com.hellomart.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value = "/error")
public class ErrorPageController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ErrorPageController.class);
	
	@RequestMapping("/400")
	public String _400(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return "error/400";
	}
	
	@RequestMapping("/403")
	public String _403(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return "error/403";
	}
	
	@RequestMapping("/404")
	public String _404(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return "error/404";
	}
	
	@RequestMapping("/500")
	public String _500(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_OK);
		return "error/500";
	}
	
}
