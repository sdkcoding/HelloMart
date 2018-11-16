package com.hellomart.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dto.ProductList;

public interface SellerService {
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath);

	public void productPartSpec(Model model, String mainCategory, String smallCategory);
	
	public Boolean PartProductValidCheck(MultipartHttpServletRequest mRequest,
			Model model,
			String mainCategory, String smallCategory);
	
	public void sellerProductRegister(Model model, MultipartHttpServletRequest mRequest,
				ProductList productList);

	public String getFileName(String productNo);

	public void delete(Model model, int no, String id);
}