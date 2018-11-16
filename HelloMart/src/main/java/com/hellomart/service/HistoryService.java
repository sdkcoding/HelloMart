package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.OrderList;
import com.hellomart.dto.ReView;

public interface HistoryService {
	public List<OrderList> historyDatelist(String id, String startDate, String endDate);
	public List<OrderList> historylist(String id);
	ReView reviewCheck(String no,String id);
}