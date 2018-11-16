package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductService;
import com.hellomart.util.XMLParser;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;
	
	@Override
	public void getDetailInfo(String no, String smallCategory, Model model) {
		if(smallCategory.equals("모바일 액세서리") || smallCategory.equals("PC 액세서리")){ 
			model.addAttribute("product", dao.getProductInfo(Integer.parseInt(no)));
		} 
		else{
			XMLParser xmlParser = new XMLParser("category.xml");
			String smallCategoryEng = xmlParser.getAttributeValue(smallCategory, "table"); 
		
			List<String> columnList = new ArrayList<>();
			List<String> columnListEng = new ArrayList<>();
		
			try {
				columnList = xmlParser.getChildren(smallCategory);
   
				for (String column : columnList) {
					columnListEng.add(xmlParser.getAttributeValue(smallCategory, column, "column"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			String sql = "select * from productlist, " + smallCategoryEng
					+ " where productlist.no = " + no
					+ " and productlist.no = " + smallCategoryEng + ".no";
		
			HashMap<String, String> map = new HashMap<>();
			map.put("sql", sql);
			model.addAttribute("columnList", columnList);
			model.addAttribute("columnListEng", columnListEng);
			model.addAttribute("detail", dao.getDetailInfo(map));
			//System.out.println("상품 상세보기 map 키 값 : " + dao.getDetailInfo(map).keySet());	
		}
	}
	
	@Override
	public ProductList getProductInfo(int no) {
		return dao.getProductInfo(no);  
	}
}