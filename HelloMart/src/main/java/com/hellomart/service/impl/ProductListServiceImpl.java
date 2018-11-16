package com.hellomart.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductListServiceImpl.class);
	
	@Autowired
	ProductListDAO dao; 
	 
	private final XMLParser xmlParser = new XMLParser("category.xml");
	
	public ProductListServiceImpl() {
	}	
	
	private Paging paging(String sql, Integer page) {
		page = page == null ? 1 : page;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sql", sql);
		int total = dao.getTotal(paramMap);
		
		return new Paging(total, page, 10, 10);
	}	
	
	private Vector<ProductList> listBoard(String sql) {
		Vector<ProductList> list = null;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sql", sql);
		list = dao.list(paramMap);
		
		if(list.isEmpty()) {
			list = null;
		}
		
		return list;
	}
	
	private void smallCategoryDetails(Map<String, Object> modelMap, String mainCategory, String smallCategory) {
		if( (smallCategory != null) && (!smallCategory.equals(""))){
			Vector<String> columnList = xmlParser.getChildren(mainCategory, smallCategory);
			HashMap<String, String> smallCategoryColumn = new HashMap<>();
			Vector<String> columnListEng = new Vector<>();
		
			for (String column : columnList) {
				String value = xmlParser.getValue(smallCategory, column);
				smallCategoryColumn.put(column, value);
				columnListEng.add(xmlParser.getAttributeValue(smallCategory, column, "column"));
			}
		
			modelMap.put("columnList", columnList);
			modelMap.put("smallCategoryColumn", smallCategoryColumn);
			modelMap.put("columnListEng", columnListEng);
		}
	}
	
	@Override
	public Map<String, Object> list(Model model) {
		Map<String, Object> requestMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) requestMap.get("request");
		
		String mainCategory = null;
		String smallCategory = null;
		Integer page = null;
		String[] checkedId = null;
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		Map<String, String[]> paramMap = new HashMap<>();
		
		while(parameterNames.hasMoreElements()) {
			String param = parameterNames.nextElement();
			switch (param) {
			case "mainCategory":
				mainCategory = request.getParameter(param);
				break;
			case "smallCategory":
				smallCategory = request.getParameter(param);
				break;
			case "page":
				page = Integer.parseInt(request.getParameter(param));
				break;
			case "checkedId":
				checkedId = request.getParameterValues("checkedId");
				break;
				default:
					String[] value = request.getParameterValues(param);
					paramMap.put(param, value);
			}
		}
		
		/***** Model에 put할 맵 생성 *****/
		Map<String, Object> modelMap = new HashMap<>();
		/***** Model에 put할 맵 생성 *****/
		
		String table = null;
		try {
			table = xmlParser.getAttributeValue(mainCategory, smallCategory, "table");
		} catch (NullPointerException e) {}
		
		/***** 페이징처리 *****/
		String sql = "SELECT Count(*) FROM ProductList";
		String totalSql = createSQL(paramMap, sql, table, mainCategory, smallCategory, null, null);
		Paging paging = paging(totalSql, page);
		modelMap.put("paging", paging);
		/***** 페이징처리 *****/
		
		/***** small 카테고리 리스트 처리 *****/
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		modelMap.put("smallCategoryList", smallCategoryList);
		/***** small 카테고리 리스트 처리 *****/
		
		if(checkedId != null) {
			Map<String, String> checked = new HashMap<>();
			for(String key : checkedId) {
				checked.put(key, "");
			}
			modelMap.put("checked", checked);
		}
		
		/***** 카테고리 세부 목록 처리 *****/
		if((!mainCategory.equals("액세서리")) && (smallCategory != null)) {
			smallCategoryDetails(modelMap, mainCategory, smallCategory);
		}
		/***** 카테고리 세부 목록 처리 *****/
		
		
		/***** SQL 생성 *****/
		int offset = paging.getOffset();
		int limit = paging.getMaxResult();
		sql = "SELECT * From ProductList";
		String listSql = createSQL(paramMap, sql, table, mainCategory, smallCategory, offset, limit);
		/***** SQL 생성 *****/
		
		
		/***** 상품 리스트 처리 *****/
		Vector<ProductList> list = listBoard(listSql);
		modelMap.put("list", list);
		/***** 상품 리스트 처리 *****/		
		
		return modelMap;
	}
	
	
	public String createSQL(
			Map<String, String[]> paramMap, String select, String table, 
			String mainCategory, String smallCategory,
			Integer offset, Integer limit){
		StringBuilder sql = new StringBuilder(select);
		
		Set<String> columns = paramMap.keySet();
		
		if((!mainCategory.equals("액세서리")) && (table != null) && (!table.isEmpty())) {
			sql
			.append(" ").append("NATURAL JOIN").append(" ")
			.append(table);
		}
		
		sql
			.append(" ").append("WHERE").append(" ");
		
		sql
			.append("mainCategory").append(" = ").append("'").append(mainCategory).append("'");
		
		if((smallCategory != null) && (!smallCategory.equals(""))) {
			sql.append(" and ").append("smallCategory").append(" = ").append("'").append(smallCategory).append("'");
		}
		
		for(String column : columns) {			
			if(!paramMap.get(column)[0].equals("")){
				boolean isFirst = true;
				
				StringBuilder sb = new StringBuilder();
				sb.append(" and (");
				for(String value : paramMap.get(column)) {
					if(!value.equals("")){
						switch (column) {
						case "search":
							sb.append("ProductName").append(" LIKE ")
							.append("'%").append(value).append("%'");
							break;
						case "price1":
							sb.append("price").append(" >= ").append(value);
							break;
						case "price2":
							sb.append("price").append(" <= ").append(value);
							break;
							default:
								if(isFirst) {
									isFirst = false;
								} else {
									sb.append(" or ");
								}
								
								if(value.indexOf('~') == -1) {
									sb
										.append(column)
										.append(" = ")
										.append("'").append(value).append("'");
								} else {
									StringTokenizer tokenizer = new StringTokenizer(value, "~");
									
									sb
										.append("(")
											.append(column)
											.append(" >= ")
											.append(filterNumber(tokenizer.nextToken()))
											.append(" and ")
											.append(column)
											.append(" <= ")
											.append(filterNumber(tokenizer.nextToken()))
										.append(")");
								}
						}
					}
				}
				sb.append(")");
				
				sql.append(sb.toString());
			}
		}
		
		if(offset != null && limit != null && offset != -1) {
			sql
				.append(" ").append("ORDER BY").append(" ")
				.append("no").append(" ")
				.append("DESC").append(" ")
				.append("LIMIT").append(" ").append(limit).append(" ")
				.append("OFFSET").append(" ").append(offset);
		}
		
		logger.debug("ProductLIst SQL : " + sql.toString());
		
		return sql.toString();
	}
	
	public String filterNumber(String str){
		return str.replaceAll("[^0-9]", "");
	}
	

	@Override
	public void updateOrderCount(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("prodNo"));
		dao.updateOrderCount(no);
	}

	@Override
	public void updateOrderCountList(HttpServletRequest request) {
		int size = Integer.parseInt(request.getParameter("size"));

		for (int i = 0; i <= size; i++) {
			int prodNo = Integer.parseInt(request.getParameter("prodNo" + i));

			dao.updateOrderCount(prodNo);
		}
	}

	@Override
	public void updateScore(int star, int no) {
		dao.updateScore(star, no);
	}
 
	@Override
	public void updateReviewCount(int no) {
		dao.updateReviewCount(no); 
	}

	@Override
	public ProductList mainlist(int no) {
		return dao.mainlist(no);
    }
	
}