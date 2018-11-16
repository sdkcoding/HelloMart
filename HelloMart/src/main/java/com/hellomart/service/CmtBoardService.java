package com.hellomart.service;

import java.util.Vector;

import com.hellomart.dto.CmtBoard;

public interface CmtBoardService {

	/* 코멘트를 가져오는 메소드 */
	Vector<CmtBoard> cmtlist(int idx, int startRow, int pageSize);
	
	/* 코멘트를 삽입하는 메소드 */
	void cmtinsert(CmtBoard cmtboard);

	/* 코멘트를 삭제하는 메소드 */
	void cmtdelete(int cmtidx);
	
	/* 코멘트의 전체 개수 불러오는 메소드 */
	int cmtCount(int idx);
}
