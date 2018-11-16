package com.hellomart.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.hellomart.dto.ProductList;

public interface ProductListService {
	Map<String, Object> list(Model model);
	void updateOrderCount(HttpServletRequest request);
	void updateOrderCountList(HttpServletRequest request);
	void updateScore(int star, int no);
	void updateReviewCount(int no);
	ProductList mainlist(int no);
}
