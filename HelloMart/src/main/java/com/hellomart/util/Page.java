package com.hellomart.util;

import org.springframework.stereotype.Component;

@Component("bbsPage")
public class Page {
	private int startRow, endRow;
	private StringBuffer sb;	
	
	public synchronized void paging(int pageNum, int totalCount, 
				int pageSize, int pageBlock, String servletPath){
		int totalPage = (int) Math.ceil((double)totalCount/pageSize);
		startRow = (pageNum - 1) * pageSize+1;
		endRow = pageNum * pageSize;		
		
		int startPage = (int)((pageNum-1)/pageBlock)*pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
				
		if(endPage > totalPage) {
			endPage = totalPage;
		}			
		
		servletPath = servletPath.substring(0, servletPath.lastIndexOf("/") + 1);
		sb = new StringBuffer();
		sb.append("<a href='");
		sb.append(servletPath + "1'");
		sb.append(" class='first'><span></span><span></span> 처음</a>");
		sb.append("<a href='");
		sb.append(servletPath);
		if(pageNum > 1){
			sb.append(pageNum - 1);
		}else{
			sb.append(pageNum);
		}
		sb.append("' class='prev'><span></span> 이전</a>");
		for(int i = startPage; i <= endPage; i++) {		
			if(i == pageNum) {
				sb.append("<strong>");
				sb.append(i);
				sb.append("</strong>");
			} else {
				sb.append("<a href='");
				sb.append(servletPath);
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}
		sb.append("<a href='");
		sb.append(servletPath);
		if(pageNum < totalPage){
			sb.append(pageNum + 1);
		}else{
			sb.append(pageNum);
		}
		sb.append("' class='next'>다음 <span></span></a>");
		sb.append("<a href='");
		sb.append(servletPath);
		sb.append(totalPage);
		sb.append("' class='last'>끝 <span></span><span></span></a>");	
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public StringBuffer getSb() {
		return sb;
	}
}
