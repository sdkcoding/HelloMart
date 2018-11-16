package com.hellomart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authenticated")
public class AuthenticatedUserContoller {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AuthenticatedUserContoller.class);
	
}