package com.hellomart.service;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.hellomart.dto.ReView;

public interface ReViewService{
	/* 해당 상품의 리뷰 목록을 가져오는 메소드 */
	Vector<ReView> listReView(int startRow, int pageSize, int no);
	
	/* 리뷰를 삽입하는 메소드 */
	void reWrite(ReView reView);
	
	/* 리뷰를 수정 하는 메소드 */
	void remodify(ReView reView);

	/* 리뷰를 삭제 하는 메소드 */
	void redelete(int idx);

	/* 상품 리스트 테이블의 리뷰 갯수를 증가시키는 메소드 */
	void updatereviewCount(HttpServletRequest request);

	/* 리뷰 하나의 정보를 가져오는 메소드 */
	ReView getReView(int idx);

	/* 페이징 처리를 위해, 해당 상품의 리뷰 갯수를 가져옴 */
	int getReCount(int no);
}