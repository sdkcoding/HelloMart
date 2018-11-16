<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>
	<div class="article_wrap">
		<h4 align="left"><b>▒ Review ▒</b></h4>
		<div id="bo_list">
			<div class="tbl_head01 tbl_wrap">
				<table>
					<c:forEach var="review" items="${list}">
						<tr class="bo_notice">
							<td width="15%" style="text-align: left; padding-left: 10px;">
								<c:forEach begin="1" end="${review.star}">★</c:forEach><c:forEach begin="${review.star + 1}" end="5">☆</c:forEach>
							</td>
							<td width="50%" style="text-align: left;">${review.content}</td>
							<td class="td_name sv_use">${review.id}</td>
							<td class="td_date">
								<fmt:formatDate value="${review.regdate}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class=BLOCK60></div>
			</div>
		</div>
		<c:if test="${pageCount>1 }">
			<div align="center" id="page">
				<c:if test="${startPage > pageBlock}">
					<a href="review?pageNum=${startPage-1}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="review?pageNum=${i }">[${i }]</a>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<a href="review?pageNum=${startPage+pageBlock}">[다음]</a>
				</c:if>
			</div>
		</c:if>
	</div>
</body>
</html>