package com.hellomart.dao;

import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface TodayViewDAO {
	Vector<ProductList> list(String[] no);
}
