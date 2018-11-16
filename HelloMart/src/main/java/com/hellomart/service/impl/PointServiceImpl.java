package com.hellomart.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dao.PointDAO;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.Point;
import com.hellomart.service.AccountService;
import com.hellomart.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	PointDAO pointDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	AccountService accountService;

	@Override
	public void insertPoint(HttpServletRequest request, OrderList orderList) {
		Point point = new Point();
		
		String incDec = request.getParameter("incDec");
		
		int totalPrice = orderList.getOrderPrice();
		int grade = accountService.getInfo(orderList.getOrderId()).getGrade();
		double qty = 0; 
		
		String content = request.getParameter("prodName"); 
		if(incDec.equals("+")){
			if(grade >= 2){
				qty = totalPrice * (0.01 * grade);
			}
			else{
				qty = totalPrice * 0.01;   
			}
			content += " 구매로 " + (int)qty + " point 적립";
		}
		if(incDec.equals("-")){
			qty = Double.parseDouble(request.getParameter("point"));
			content += " 구매에 " + (int)qty + " point 사용";
		}
		
		point.setId(orderList.getOrderId());
		point.setIncDec(incDec); 
		point.setPoint((int)Math.ceil(qty));
		point.setContent(content);
		
		pointDao.insertPoint(point); 
		accountDao.updatePoint(point.getId(), point.getPoint(), point.getIncDec());
	}

	@Override
	public void insertPointList(HttpServletRequest request) {
		Point point = new Point();

		String incDec = request.getParameter("incDec");
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int size = Integer.parseInt(request.getParameter("size"));
		String id = request.getParameter("orderId");
		
		int grade = accountService.getInfo(id).getGrade();
		double qty = 0; 

		String content = request.getParameter("prodName0");
		if (incDec.equals("+")) {
			if(grade >= 2){
				qty = totalPrice * (0.01 * grade);
			}
			else{
				qty = totalPrice * 0.01;   
			}
			content += " 외 " + size + "개 상품 구매로 " + (int)qty + " point 적립";
		}
		if (incDec.equals("-")) {
			qty = Double.parseDouble(request.getParameter("point"));
			content += " 외 " + size + "개 상품 구매에 " + (int)qty + " point 사용";
		}

		point.setId(id);
		point.setIncDec(incDec);
		point.setPoint((int) Math.ceil(qty));
		point.setContent(content);

		pointDao.insertPoint(point);
		accountDao.updatePoint(point.getId(), point.getPoint(), point.getIncDec());
	}

	@Override
	public List<Point> getAllPointLog(String id) {
		return pointDao.getAllPointLog(id);
	}

	@Override
	public List<Point> getPeriodPointLog(String id, String startDate, String endDate) {
		return pointDao.getPeriodPointLog(id, startDate, endDate);
	} 
}