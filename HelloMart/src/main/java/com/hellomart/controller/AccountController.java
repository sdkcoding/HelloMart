package com.hellomart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;
import com.hellomart.validator.JoinFormValidator;

@Controller
public class AccountController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private JoinFormValidator validator;
	
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public ModelAndView join() {
		return new ModelAndView("account/join", "account", new Account());
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String joinProcess(Model model,
			@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
		
		validator.validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			Map<String, String> map = new HashMap<>();
			map.put("selectedYear", account.getBirthYear());
			map.put("selectedMonth", account.getBirthMonth());
			map.put("selectedDay", account.getBirthDay());
			
			model.addAttribute("birthdate", map);
			return "account/join";
		}
		
		service.insertAccount(account);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/idpw_search", method=RequestMethod.GET)
	public String idpw_search() {
		return "account/idpw_search";
	}
		
	@RequestMapping(value="/idpw_search", method=RequestMethod.POST)
	public ModelAndView idpw_search_do(@RequestParam("email") String email) {
		ModelAndView mav = new ModelAndView(); 
		StringBuilder sb = new StringBuilder();
		
		service.searchIDPW(email);
	
		sb.append("redirect:/idpw_search");
		sb.append("?submit=1");
			
		mav.setViewName(sb.toString());
		return mav;
	}
	
}