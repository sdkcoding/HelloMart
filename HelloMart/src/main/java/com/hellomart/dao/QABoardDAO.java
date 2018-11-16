package com.hellomart.dao;

import java.util.Map;
import java.util.Vector;

import com.hellomart.dto.QABoard;

public interface QABoardDAO {

	/* 게시글의 전체 개수 불러오는 메소드 */
	int getCount(Map<String, Object> map);
   
	/* 게시글을 가져오는 메소드 */
	Vector<QABoard> list(Map<String, Object> map);
	   
	/* 게시글을 삽입하는 메소드 */
	void insertQABoard(QABoard qaboard);
	   
	/* 게시글 카운트 증가 메소드 */
	void viewCount(int idx);
	   
	/* 게시글 뷰화면 메소드 */
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