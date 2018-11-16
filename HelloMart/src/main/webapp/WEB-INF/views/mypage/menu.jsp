<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style type="text/css">
.myshopMain {
	overflow: hidden;
	width: 800px;
	text-align: center;
	border-left: 1px solid #ddd;
	border-top: 1px solid #ddd;
	margin: 60px auto;
}
.myshopMain .shopMain {
	float: left;
	width: 399px;
	border-right: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}
.myshopMain .shopMain:hover {
	background: #E9E9E9;
}
.myshopMain .shopMain .tit {
	color: #333;
	display: block;
	padding: 25px 20px;
	font-size: 12px;
	font-family: sans-serif;
	font-weight: bold;
}
.myshopMain .shopMain a {
	display: block;
	font-size: 10px;
	text-decoration: none;
}
</style>
<script>
function seller_reg() {
	if('${param.success}' == 1) {
		alert('판매자 신청이 완료되었습니다.');
	}
}
</script>
</head>
<body onload="seller_reg()">
<sec:authentication var="id" property="principal"/>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="BLOCK20"></div>
<div class="titbox">
	<div class="title">
		<span class="name">MY PAGE</span>
	</div>
</div>
<div class="myshopMain">
	<div class="shopMain">
		<a href="/mypage/info"> 
			<span class="tit">MODIFY<br>(회원정보)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/history"> 
			<span class="tit">ORDER LIST<br>(주문내역)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/cartlist"> 
			<span class="tit">CART<br>(장바구니)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/mypage/point"> 
			<span class="tit">MY POINT<br>(적립금내역)</span>
		</a>
	</div>
	<div class="shopMain">
		<a href="/todayView">
			<span class="tit">TODAY VIEW<br>(오늘 본 상품)${account}</span>
		</a>
	</div>
	<div class="shopMain">
		<sec:authorize access="hasRole('ROLE_MEMBER')">
			<c:if test="${status eq 'NOTHING'}">
				<a href="/mypage/sellerRegist"> 
					<span class="tit">SELLER REG<br>(판매자 신청)</span>
				</a>
			</c:if>
			<c:if test="${status eq 'SELLER_READY'}">
					<span class="tit">SELLER READY<br>(판매자 승인 대기)</span>
			</c:if>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_SELLER')">
			<a href="/seller/page/1">
				<span class="tit">SELLER PAGE<br>(판매자 페이지)</span>
			</a>
		</sec:authorize>
	</div>
	
</div>
<div class="BLOCK60"></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>