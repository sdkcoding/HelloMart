package com.hellomart.service;

import org.springframework.ui.Model;

import com.hellomart.dto.ProductList;

public interface ProductService {

	void getDetailInfo(String no, String smallCategory, Model model);
	ProductList getProductInfo(int no);
}