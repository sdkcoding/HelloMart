package com.hellomart.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String sellerProductList(@PathVariable String pageNumString, 
								Model model, Principal principal,
								HttpServletRequest request) {
		int pageNum = Integer.parseInt(pageNumString);
		String servletPath = request.getServletPath();
		String id = principal.getName();
		sellerService.getSellerProductList(pageNum, model, id, servletPath);
		return "seller/sellerProductList";
	}
	
	@RequestMapping(value="/productRegister", method=RequestMethod.GET)
	public String sellerProductRegister(@RequestParam("mainCategoryInput") 
										String mainCategoryInput,
										@RequestParam("smallCategoryInput")
										String smallCategoryInput,
										Model model){
		sellerService.productPartSpec(model, mainCategoryInput, smallCategoryInput);
		model.addAttribute("ProductList", new ProductList());
		return "seller/productRegister";
	}
	
	@RequestMapping(value="/productRegister" ,method=RequestMethod.POST)
	public String sellerProductRegister(MultipartHttpServletRequest mRequest, 
			HttpServletRequest request,
			@ModelAttribute("ProductList") ProductList productList,
			BindingResult bindingResult, Principal principal, Model model){
		String uri = null;
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("ProductList", new ProductList());
			sellerService.PartProductValidCheck(mRequest, model,
				productList.getMainCategory(), productList.getSmallCategory());
			return "seller/productRegister";
		}

		if(!sellerService.PartProductValidCheck(mRequest,model,
				productList.getMainCategory(), productList.getSmallCategory())){
			uri = "seller/productRegister";
		}else{
			model.addAttribute("request", request);
			productList.setRegisterID(principal.getName());
			sellerService.sellerProductRegister(model, mRequest, productList);
			uri = "redirect:/seller/page/1";
		}
		return uri;
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, HttpServletRequest request,
			@RequestParam("no") int no,
			Principal principal) {
		String id = principal.getName();
		
		model.addAttribute("request", request);
		sellerService.delete(model, no, id);
		
		return "redirect:/seller/page/1";
	}
	
}