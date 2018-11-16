package com.hellomart.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;


@Controller
public class MainController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	 ProductListService service;
	
	@RequestMapping("/")
	   public String index(Model model) {
	      
	      List<ProductList> list = new ArrayList<ProductList>();
	      int[] noList = new int[16];
	      
	      for(int i=0;i<noList.length;i++){
	         ProductList productlist = new ProductList();
	         noList[i] = (int)(Math.random() * 100) + 40;
	         
	         productlist = service.mainlist(noList[i]);
	         list.add(productlist);
	      }
	      
	      model.addAttribute("list", list);
	      return "index";
	   }

}
