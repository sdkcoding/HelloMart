package com.hellomart.service;

import com.hellomart.dto.OrderList;
import javax.servlet.http.HttpServletRequest;

public interface OrderService {
	public void insertOrder(OrderList orderList);
	public void insertOrderList(HttpServletRequest request);
}
