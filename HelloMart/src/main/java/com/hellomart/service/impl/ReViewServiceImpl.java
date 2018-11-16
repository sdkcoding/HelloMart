package com.hellomart.service.impl;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.ReViewDAO;
import com.hellomart.dto.ReView;
import com.hellomart.service.ReViewService;

@Service
public class ReViewServiceImpl implements ReViewService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReViewService.class);
	
	@Autowired
	private ReViewDAO dao;
	
	public ReViewServiceImpl() { }

	@Override
	public Vector<ReView> listReView(int no,int startRow, int pageSize) {
		return dao.listReView(no, startRow, pageSize);
	}

	@Override
	public void reWrite(ReView reView) {
		dao.reWrite(reView);		
	}

	@Override
	public void remodify(ReView reView) {
		dao.remodify(reView);
	}

	@Override
	public void redelete(int idx) {
		dao.redelete(idx);
	}
	
	@Override
	public void updatereviewCount(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("prodNo"));
		dao.updatereviewCount(no);
	}

	@Override
	public ReView getReView(int idx) {
		return dao.getReView(idx);
	}

	@Override
	public int getReCount(int no) {
		return dao.getReCount(no);
	}
}