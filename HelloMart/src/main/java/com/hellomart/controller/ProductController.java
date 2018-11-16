package com.hellomart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.service.ProductService;
import com.hellomart.util.TodayViewUtils;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/productView")
	public String view(
			@RequestParam("no") String no, Model model,
			@RequestParam("smallCategory") String smallCategory, 
			HttpServletRequest request, HttpServletResponse response) {
		TodayViewUtils todayViewUtils = new TodayViewUtils(request, response);

		service.getDetailInfo(no, smallCategory, model);
		
		todayViewUtils.addTodayView(no);
		
		return "product/productView";
	}
}