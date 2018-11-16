package com.hellomart.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.dto.Account;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.ProductList;
import com.hellomart.service.AccountService;
import com.hellomart.service.CartService;
import com.hellomart.service.OrderService;
import com.hellomart.service.PointService;
import com.hellomart.service.ProductListService;
import com.hellomart.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PointService pointService;
	
	@Autowired
	ProductListService productListService;
	
	@Autowired
	CartService cartService;

	// 상품 상세 보기 페이지에서 구매 버튼을 눌러서 구매 페이지로 이동
	@RequestMapping("/buy")
	public String buy(Model model, HttpServletRequest request){
		String no = request.getParameter("no");
		String smallCategory = request.getParameter("smallCategory");
		
		// 구매상품정보
		productService.getDetailInfo(no, smallCategory, model);  
		// 상품구매수량
		model.addAttribute("orderCount", request.getParameter("orderCount"));
		// 구매자 정보
		Account account = accountService.getInfo(request.getParameter("id"));
		model.addAttribute("account", account);
		
		return "product/productBuy";
	}
	
	@RequestMapping("/orderOk")
	public String orderOk() {
		return "product/orderOk";
	}
	
	// 단일 상품 구매 페이지에서 결제하기 버튼 눌렀을 때
	@RequestMapping("/buyOk")
	public String buyOk(OrderList orderList, HttpServletRequest request){
		orderService.insertOrder(orderList);
		
		pointService.insertPoint(request, orderList);
		
		productListService.updateOrderCount(request);
		
		return "redirect:/orderOk";
	}
	
	// 장바구니에서 구매 버튼을 눌렀을 때
	@RequestMapping("/cartBuy")
	public String cartBuy(Model model, @RequestParam int[] orderCount, @RequestParam int[] no, Principal principal){
		List<Integer> list = new ArrayList<>();
		List<ProductList> list2 = new ArrayList<>();
				
		for(int i=0; i<no.length; i++){
			list.add(orderCount[i]);
			list2.add(productService.getProductInfo(no[i]));
		}
		
		model.addAttribute("productList", list2);
		model.addAttribute("account", accountService.getInfo(principal.getName()));
		model.addAttribute("orderCountList", list);
		return "product/productCartBuy";
	}
	
	// 복수 상품 구매 페이지에서 결제하기 버튼 눌렀을 때
	@RequestMapping("/cartBuyOk")
	public String cartBuyOk(HttpServletRequest request, Principal principal){
		pointService.insertPointList(request);
		 
		orderService.insertOrderList(request); 
		
		productListService.updateOrderCountList(request);
		
		cartService.deleteCartList(principal.getName());
		
		return "redirect:/orderOk";
	}
}