package com.hellomart.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hellomart.dto.ProductList;

public interface SellerDAO {
	public ArrayList<ProductList> getSellerProductList(HashMap<String, Object> paramMap);
	public int getSellerProductCount(String id);
	public void insertProductInfo(ProductList productList);
	public int getNoProductList();
	public void insertPartProductInfo(Map<String, Object> sqlMap);
	public String getFilePath(int no);
	public int delete(int no, String id);
}