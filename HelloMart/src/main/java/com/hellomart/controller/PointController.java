package com.hellomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.service.AccountService;

@Controller
public class PointController {
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/pointView")
	public String pointView(String id, Model model){
		model.addAttribute("point", accountService.getInfo(id).getPoints());
		
		return "product/pointView";
	}
}
