package com.hellomart.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dao.SellerDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;
import com.hellomart.util.Page;
import com.hellomart.util.FileUtils;
import com.hellomart.util.XMLParser;

@Service
public class SellerServiceImpl implements SellerService{
	
	private static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
	
	@Autowired
	SellerDAO dao;
	
	@Resource(name = "bbsPage")
	private Page page;
	
	@Autowired
	private FileUtils upload;
	
	private List<TableInformation> tableList;
	
	public SellerServiceImpl() {
		tableList = tableInfoXmlParser();
	}
	
	@Override
	public void delete(Model model, int no, String id) {
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) modelMap.get("request");
		HttpSession session = request.getSession();
		
		String path = dao.getFilePath(no);
		String rootPath = session.getServletContext().getRealPath(path);
		
		int result = dao.delete(no, id);
		if(result == 1) {
			FileUtils.delete(rootPath);
		}
	}
	
	@Override
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath) {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		HashMap<String, Object> paramMap;
		
		totalCount = dao.getSellerProductCount(id);
		page.paging(pageNum, totalCount, pageSize, pageBlock, servletPath);
		paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		paramMap.put("id", id);
		
		ArrayList<ProductList> sellerProductList = dao.getSellerProductList(paramMap);
		HashMap<String, Object> sellerProductMap;
		ArrayList<HashMap<String,Object>> sellerProdReviewList
			= new ArrayList<HashMap<String,Object>>();
		for(ProductList i : sellerProductList){
			sellerProductMap = new HashMap<String, Object>();
			sellerProductMap.put("no", i.getNo());
			sellerProductMap.put("imagePath", i.getImagePath());
			sellerProductMap.put("ProductName", i.getProductName());
			sellerProductMap.put("MfCompany", i.getMfCompany());
			sellerProductMap.put("MainCategory", i.getMainCategory());
			sellerProductMap.put("SmallCategory", i.getSmallCategory());
			sellerProductMap.put("Price", i.getPrice());
			sellerProductMap.put("Score", i.getScore());
			sellerProductMap.put("OrderCount", i.getOrderCount());
			sellerProductMap.put("count", i.getReviewCount());
			sellerProdReviewList.add(sellerProductMap);
		}
		model.addAttribute("id", id);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCode", page.getSb().toString());
		model.addAttribute("mapList", sellerProdReviewList);
	}

	@Override
	public void productPartSpec(Model model, String mainCategory, String smallCategory) {
		
		TableInformation tableInfo = null;
		for(int i = 0 ; i < tableList.size() ; i++){
			if(tableList.get(i).getTableKorName().equals(smallCategory)){
				tableInfo = tableList.get(i);
			}
		}

		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategory", smallCategory);
		model.addAttribute("smallCategoryEng", tableInfo.getTableEngName());
		model.addAttribute("specList", tableInfo.getColumnValueList());
		model.addAttribute("specEngNameList", tableInfo.getColumnEngNameList());
		model.addAttribute("specKorNameList", tableInfo.getColumnKorNameList());
		model.addAttribute("specTypeList", tableInfo.getColumnTypeList());
		model.addAttribute("specUnitList", tableInfo.getColumnUnitList());
	}
	
	

	@Override
	public Boolean PartProductValidCheck(MultipartHttpServletRequest mRequest, 
			Model model, String mainCategory, String smallCategory) {
		
		Boolean flag = true;
		TableInformation tableInfo = null;
		for(int i = 0 ; i < tableList.size() ; i++){
			if(tableList.get(i).getTableKorName().equals(smallCategory)){
				tableInfo = tableList.get(i);
			}
		}
		if(mRequest.getFile("productImageFile").isEmpty()){
			model.addAttribute("fileMsg", "업로드가 안 되었습니다.");
			flag = false;
		}else{
			String imageFileName = mRequest.getFile("productImageFile").getOriginalFilename();
			Long imageFileSize = mRequest.getFile("productImageFile").getSize();
			String extension = imageFileName.substring(imageFileName.lastIndexOf(".") + 1, imageFileName.length());
			if((!extension.toLowerCase().equals("gif")) 
				&& (!extension.toLowerCase().equals("png"))
				&& (!extension.toLowerCase().equals("jpg"))
				&& (!extension.toLowerCase().equals("jpeg"))){
				model.addAttribute("fileMsg", "확장자가 맞지 않습니다.");
				flag = false;
			}else if(imageFileSize > 1*1024*1024){
				model.addAttribute("fileMsg", "크기는 1MB이하입니다.");
				flag = false;
			}
		}
		
		List<String> parameters = new ArrayList<String>();
		List<String> errorMessages = new ArrayList<String>();
		for(int i = 0 ; i < tableInfo.getColumnEngNameList().size() ; i++){
			String requestValue 
				= mRequest.getParameter(tableInfo.getColumnEngNameList().get(i));
			if(requestValue != ""){
				parameters.add(requestValue);
				errorMessages.add(" ");
			}else{
				flag = false;
				errorMessages.add(tableInfo.getColumnKorNameList().get(i) + "가 입력되어 있지 않습니다.");
			}
		}
		model.addAttribute("specKorNameError", errorMessages);
		if(!flag){
			System.out.println("체크 통과 못함");
			productPartSpec(model, mainCategory, smallCategory);
		}
		return flag;
	}

	@Override
	public void sellerProductRegister(Model model, MultipartHttpServletRequest mRequest, ProductList productList) {
		Map<String, Object> modelMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) modelMap.get("request");
		HttpSession session = request.getSession();
		String rootPath = session.getServletContext().getRealPath("/");
		String attachPath = "resources/images/product/";
		String realPath = rootPath + attachPath;
		
		logger.debug("이미지 업로드 주소 : " + realPath);
		
		Vector<String> filenames = null;
		
		try {
			filenames = upload.fileUpload(mRequest, realPath);
		} catch (IllegalStateException | IOException e) {
			return;
		}
		
		productList.setImagePath("/" + attachPath + filenames.get(0));
		dao.insertProductInfo(productList);
		
		TableInformation tableInfo = null;
		for(int i = 0 ; i < tableList.size() ; i++){
			if(tableList.get(i).getTableKorName().equals(productList.getSmallCategory())){
				tableInfo = tableList.get(i);
			}
		}
		
		Map<String, Object> productPartSpecColumnMap = new HashMap<String, Object>();
		
		for(int i = 0 ; i < tableInfo.getColumnEngNameList().size() ; i++){
			String columnValue = mRequest.getParameter(tableInfo.getColumnEngNameList().get(i));
			String columnName = tableInfo.getColumnEngNameList().get(i);
			String columnType = tableInfo.getColumnTypeList().get(i);
			if(columnType.equals("Integer")){
				int resultColumnValue = Integer.parseInt(columnValue);
				productPartSpecColumnMap.put(columnName, resultColumnValue);
			}else if(columnType.equals("Double")){
				double resultColumnValue = Double.parseDouble(columnValue);
				productPartSpecColumnMap.put(columnName, resultColumnValue);
			}else{
				productPartSpecColumnMap.put(columnName, columnValue);
			}
		}
		
		int no = dao.getNoProductList();
		
		String tableName = tableInfo.getTableEngName();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(tableName);
		sql.append(" (No, ");
		int i;
		for(i = 0 ; i < tableInfo.getColumnEngNameList().size() - 1;i++){
			sql.append(tableInfo.getColumnEngNameList().get(i) + ", ");
		}
		sql.append(tableInfo.getColumnEngNameList().get(i) + ") ");
		sql.append("VALUES(");
		sql.append(no + ", ");
		for(i = 0 ; i < tableInfo.getColumnTypeList().size() - 1 ; i++){
			if(tableInfo.getColumnTypeList().get(i).equals("Integer")){
				int result 
					= (int)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append(result + ", ");
			}else if(tableInfo.getColumnTypeList().get(i).equals("Double")){
				double result 
					= (double)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append(result + ", ");
			}else{
				String result 
					= (String)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append("'");
				sql.append(result);
				sql.append("', ");
			}
		}
		if(tableInfo.getColumnTypeList().get(i).equals("Integer")){
			int result 
				= (int)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append(result + ")");
		}else if(tableInfo.getColumnTypeList().get(i).equals("Double")){
			double result 
				= (double)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append(result + ")");
		}else{
			String result 
				= (String)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append("'");
			sql.append(result);
			sql.append("')");
		}
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("sql", sql.toString());
		dao.insertPartProductInfo(sqlMap);
	}
	
	@Override
	public String getFileName(String productNo) {
		int no = Integer.parseInt(productNo);
		String filePath = dao.getFilePath(no);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		return fileName;
	}

	private static List<TableInformation> tableInfoXmlParser() {
		XMLParser xmlParser = new XMLParser("category.xml");
		
		List<TableInformation> tableList = new ArrayList<TableInformation>();
		TableInformation tableInfo;
		List<String> mainCategorys = xmlParser.getChildren("카테고리"); 
		Map<String, List<String>> categoryMap = new HashMap<String, List<String>>();
		List<String> smallCategorys;
		for(int i = 0 ; i < mainCategorys.size() ; i++){
			smallCategorys = xmlParser.getChildren(mainCategorys.get(i));
			categoryMap.put(mainCategorys.get(i), smallCategorys);
		}
		
		StringBuffer sBuffer; Pattern p; Matcher m;
		
		StringTokenizer tokenizer;
		String valueList = null;
		String specValue = null;
		List<String> tokenResultValueList;
		
		for(int i = 0 ; i < mainCategorys.size() ; i++){
			smallCategorys = categoryMap.get(mainCategorys.get(i));
			for(int j = 0 ; j < smallCategorys.size() ; j++){
				tableInfo = new TableInformation();
				tableInfo.setColumnEngNameList(new ArrayList<String>());
				tableInfo.setColumnTypeList(new ArrayList<String>());
				tableInfo.setColumnUnitList(new ArrayList<String>());
				tableInfo.setColumnValueList(new ArrayList<List<String>>());
				tableInfo.setTableKorName(smallCategorys.get(j));
				tableInfo.setTableEngName(xmlParser.getAttributeValue(smallCategorys.get(j), "table"));
				tableInfo.setColumnKorNameList(xmlParser.getChildren(mainCategorys.get(i), smallCategorys.get(j)));
				for (String columnKorName : tableInfo.getColumnKorNameList()) {
					valueList = xmlParser.getValue(smallCategorys.get(j), columnKorName);

					tableInfo.getColumnEngNameList()
						.add(xmlParser.getAttributeValue(smallCategorys.get(j), columnKorName, "column"));
					
					String columnType = xmlParser.getAttributeValue(smallCategorys.get(j), columnKorName, "type");
					
					tableInfo.getColumnTypeList().add(columnType);
					
					if(columnType.equals("Integer") || columnType.equals("Double")){
						String[] value = valueList.split(",", 2);
						sBuffer = new StringBuffer();
						p = Pattern.compile("[^-?\\d+(,\\d+)*?\\.?\\d+?]+");
						m = p.matcher(value[0].trim());
						while (m.find()) {
							sBuffer.append(m.group());
						}
						String stringUnit = sBuffer.toString();
						if(stringUnit.indexOf("~") >= 0){
							String[] tempUnit = stringUnit.split("~");
							if(tempUnit.length == 0){
								tableInfo.getColumnUnitList().add(" ");
							}else{
								tableInfo.getColumnUnitList().add(tempUnit[1]);
							}
						}else{
							if(stringUnit.isEmpty()){
								tableInfo.getColumnUnitList().add(" ");
							}else{
								tableInfo.getColumnUnitList().add(stringUnit);
							}
						}
						tableInfo.getColumnValueList().add(new ArrayList<String>());
					}else{
						tokenResultValueList = new ArrayList<String>();
						tokenizer = new StringTokenizer(valueList.trim(), ",");
						while(tokenizer.hasMoreTokens()){ 
							specValue = tokenizer.nextToken();
							tokenResultValueList.add(specValue.trim());	
						}
						tableInfo.getColumnValueList().add(tokenResultValueList);
						tableInfo.getColumnUnitList().add(" ");
					}
				}
				tableList.add(tableInfo);
			}
		}
		return tableList;
	}
}

class TableInformation {
	private String tableEngName;
	private String tableKorName;
	private List<String> columnTypeList;
	private List<String> columnEngNameList;
	private List<String> columnKorNameList;
	private List<String> columnUnitList;
	private List<List<String>> columnValueList;

	public TableInformation() {}

	public String getTableEngName() {
		return tableEngName;
	}

	public void setTableEngName(String tableEngName) {
		this.tableEngName = tableEngName;
	}

	public String getTableKorName() {
		return tableKorName;
	}

	public void setTableKorName(String tableKorName) {
		this.tableKorName = tableKorName;
	}

	public List<String> getColumnTypeList() {
		return columnTypeList;
	}

	public void setColumnTypeList(List<String> columnTypeList) {
		this.columnTypeList = columnTypeList;
	}

	public List<String> getColumnEngNameList() {
		return columnEngNameList;
	}

	public void setColumnEngNameList(List<String> columnEngNameList) {
		this.columnEngNameList = columnEngNameList;
	}

	public List<String> getColumnKorNameList() {
		return columnKorNameList;
	}

	public void setColumnKorNameList(List<String> columnKorNameList) {
		this.columnKorNameList = columnKorNameList;
	}
	
	public List<String> getColumnUnitList() {
		return columnUnitList;
	}

	public void setColumnUnitList(List<String> columnUnitList) {
		this.columnUnitList = columnUnitList;
	}

	public List<List<String>> getColumnValueList() {
		return columnValueList;
	}

	public void setColumnValueList(List<List<String>> columnValueList) {
		this.columnValueList = columnValueList;
	}

	@Override
	public String toString() {
		return "TableInformation [tableEngName=" + tableEngName + ", tableKorName=" + tableKorName + ", columnTypeList="
				+ columnTypeList + ", columnEngNameList=" + columnEngNameList + ", columnKorNameList="
				+ columnKorNameList + ", columnUnitList=" + columnUnitList + ", columnValueList=" + columnValueList
				+ "]";
	}
	
}