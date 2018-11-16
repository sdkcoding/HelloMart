package com.hellomart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.HistoryDAO;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.ReView;
import com.hellomart.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HistoryService.class);
	
	@Autowired
	private HistoryDAO dao;

	@Override
	public List<OrderList> historylist(String id) {
		return dao.historylist(id);
	}

	@Override
	public List<OrderList> historyDatelist(String id, String startDate, String endDate) {
		return dao.historyDatelist(id, startDate, endDate);
	} 	
	
	@Override
	public ReView reviewCheck(String no, String id) {
 		return dao.reviewCheck(no, id);
 	}
}