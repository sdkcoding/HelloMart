package com.hellomart.service.impl;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.OrderDAO;
import com.hellomart.dto.OrderList;
import com.hellomart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO dao;
	
	@Override
	public void insertOrder(OrderList orderList) {
		dao.insertOrder(orderList); 
	}


	@Override
	public void insertOrderList(HttpServletRequest request) {
		int size = Integer.parseInt(request.getParameter("size"));

		OrderList orderList = new OrderList();
		
		orderList.setOrderId(request.getParameter("orderId"));
		orderList.setReceiverName(request.getParameter("receiverName"));
		orderList.setReceiverPhone(request.getParameter("receiverPhone"));
		orderList.setReceiverDetailAddress(request.getParameter("receiverDetailAddress"));
		orderList.setReceiverPostCode(request.getParameter("receiverPostCode"));
		orderList.setReceiverRoadAddress(request.getParameter("receiverRoadAddress"));
		orderList.setOrderStatus(request.getParameter("orderStatus"));
		
		for(int i=0; i<=size; i++){
			int orderCount = Integer.parseInt(request.getParameter("orderCount" + i));
			int orderPrice = Integer.parseInt(request.getParameter("orderPrice" + i));
			int prodNo = Integer.parseInt(request.getParameter("prodNo" + i));
			
			orderList.setOrderCount(orderCount);
			orderList.setOrderPrice(orderPrice);
			orderList.setProdNo(prodNo);
			
			dao.insertOrder(orderList);
		}		
	}	
}