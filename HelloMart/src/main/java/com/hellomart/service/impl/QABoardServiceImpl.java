package com.hellomart.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.QABoardDAO;
import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;
import com.hellomart.util.Paging;

@Service
public class QABoardServiceImpl implements QABoardService{
   
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardService.class);

	@Autowired
	private QABoardDAO dao;

	private Paging paging(Integer page, int maxResult, int pagePerBlock, String searchOption, String keyword) {
		page = page == null ? 1 : page;
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("searchOption", searchOption);
		paramMap.put("keyword", keyword);
		int total = dao.getCount(paramMap);
		
		return new Paging(total, page, maxResult, pagePerBlock);
	}
   
	private Vector<QABoard> list(int offset, int limit, String searchOption, String keyword) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("offset", offset);
		paramMap.put("limit", limit);
		paramMap.put("searchOption", searchOption);
		paramMap.put("keyword", keyword);
		
		return dao.list(paramMap);
	}
	
	@Override
	public Map<String, Object> list(Integer page, int maxResult, int pagePerBlock, String searchOption, String keyword) {
		Map<String, Object> modelMap = new HashMap<>();

		Paging paging = paging(page, maxResult, pagePerBlock, searchOption, keyword);
		modelMap.put("paging", paging);
		
		int offset = paging.getOffset();
		Vector<QABoard> list = null;
		if(offset != -1) {
			int limit = paging.getMaxResult();
			list = list(offset, limit, searchOption, keyword);
		}
		modelMap.put("list", list);
		
		return modelMap;
	}

	@Override
	public void insertQABoard(QABoard qaboard) {
		dao.insertQABoard(qaboard);
	}
   
	@Override
	public QABoard viewQABoard(int idx) {
		return dao.viewQABoard(idx);
	}

	@Override
	public void viewCount(int idx) {
		dao.viewCount(idx);   
	}

	public QABoardServiceImpl() {
	}

	@Override
	public void modify(QABoard qaboard) {
		dao.modify(qaboard);
	}

	@Override
	public void delete(int idx) {
		dao.delete(idx);      
	}

	@Override
	public void cmtinc(int idx) {
		dao.cmtinc(idx);
	}

	@Override
	public void cmtdec(int idx) {
		dao.cmtdec(idx);
	}

}