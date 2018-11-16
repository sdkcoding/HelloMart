package com.hellomart.controller;

import java.security.Principal;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.ReView;
import com.hellomart.service.ProductListService;
import com.hellomart.service.ReViewService;

@Controller
public class ReViewController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReViewController.class);

	@Autowired
	private ReViewService service;
	
	@Autowired
	private ProductListService productListService;

	@RequestMapping("/review")
	public ModelAndView reViewList(String pageNum, int no) {
		ModelAndView mav = new ModelAndView();
		
		int pageSize = 5;
		int startPage = 0;
		int endPage=0;
		int pageBlock=0;
		int pageCount = service.getReCount(no);
		
		if(pageNum == null){
			pageNum ="1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize;
		
		Vector<ReView> list=null;
		
		if(pageCount>0){
			//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
			list = service.listReView(no,startRow, pageSize);
			pageCount=pageCount/pageSize+(pageCount%pageSize==0?0:1);
			pageBlock=3;
			
			startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
			
			endPage=startPage+pageBlock-1;
					
				if(endPage > pageCount){
					
					endPage = pageCount;
				}
		}
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageBlock", pageBlock);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageCount", pageCount);
		mav.addObject("list",  list);
		mav.setViewName("review/ReViewList");
		
		return mav;
	}
	
	@RequestMapping(value = "/reWrite", method=RequestMethod.GET)
	public ModelAndView write(ReView review) {	
		ModelAndView mav = new ModelAndView();		
		mav.addObject("review", review);		
		mav.setViewName("review/reWrite");
		return mav;
	}
	
	@RequestMapping(value = "/reWrite", method=RequestMethod.POST)
	public String writeProcess(ReView review, Principal principal){
		String id = principal.getName();
		service.reWrite(review);
		productListService.updateScore(review.getStar()*20,review.getNo());
		productListService.updateReviewCount(review.getNo());
		
		return "redirect:/mypage/history?id="+id;
	}
	

	@RequestMapping(value = "/remodify", method = RequestMethod.GET)
	public ModelAndView remodify(int idx) {
		ModelAndView mav = new ModelAndView();
		ReView reView = service.getReView(idx);
		mav.addObject("reView", reView);
		mav.setViewName("review/remodify");
		return mav;
	}

	@RequestMapping(value = "/remodify", method = RequestMethod.POST)
	public String remodifyProcess(ReView reView) {
		service.remodify(reView);
		return "redirect:/mypage/history";
	}

	@RequestMapping(value = "/redelete", method = RequestMethod.GET)
	public String redeleteProcess(int idx) {
		service.redelete(idx);
		return "redirect:/mypage/history";
	}
}