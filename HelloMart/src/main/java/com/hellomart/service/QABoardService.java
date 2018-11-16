package com.hellomart.service;

import java.util.Map;

import org.springframework.security.access.prepost.PostAuthorize;

import com.hellomart.dto.QABoard;

public interface QABoardService {

	/* 게시글을 가져오는 메소드 */
	Map<String, Object> list(Integer page, int maxResult, int pagePerBlock, String searchOption, String keyword);
	
	/* 게시글을 삽입하는 메소드 */
	void insertQABoard(QABoard qaboard);
   
   /* 게시글 카운트 증가 메소드 */
   void viewCount(int idx);
   
   /* 게시글 뷰화면 메소드 */
   @PostAuthorize("returnObject.id == authentication.name or hasRole('ROLE_ADMIN')")
   QABoard viewQABoard(int idx);
   
   /* 글 수정 하는 메소드 */
   void modify(QABoard qaboard);

   /* 글 삭제 하는 메소드 */
   void delete(int idx);
   
   /* 코멘트 작성시 카운트 증가 */
   void cmtinc(int idx);
   
   /* 코멘트 삭제시 카운트 감소 */
   void cmtdec(int idx);
   
}