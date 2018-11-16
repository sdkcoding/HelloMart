package com.hellomart.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.service.ProductListService;

@Controller
@RequestMapping("/productList")
public class ProductListController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductListController.class);
	
	@Autowired
	ProductListService service;
	
	@RequestMapping("")
	public String productMainList(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		
		Map<String, Object> attributes = service.list(model);

		model.addAllAttributes(attributes);
		
		return "product/productList";
	}
}