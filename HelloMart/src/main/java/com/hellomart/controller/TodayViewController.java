package com.hellomart.controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dao.TodayViewDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.util.TodayViewUtils;

@Controller
public class TodayViewController {
	 
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TodayViewController.class);
	
	@Autowired
	private TodayViewDAO dao;
	
	@RequestMapping("/todayView")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		TodayViewUtils todayViewUtils = new TodayViewUtils(request, response);
			
		ModelAndView mav = new ModelAndView();
			
		Vector<String> no = todayViewUtils.getAllValue(10);

		if(!no.isEmpty()) {
			
			Vector<ProductList> plList = dao.list(no.toArray(new String[no.size()]));
			
			if(!plList.isEmpty()) { 
			
				int size = plList.size();
				int maxIndex = size - 1;
				
				ProductList[] todayView = new ProductList[plList.size()];
				for(int i = 0; i < size; i++) {
					int index = no.indexOf("" + plList.get(i).getNo());
					todayView[maxIndex - index] = plList.get(i);
				}
				
				mav.addObject("todayView", todayView);

			}
			
		}
		
		mav.setViewName("mypage/todayView");
		return mav;
	}
	
}