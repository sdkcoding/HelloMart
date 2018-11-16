<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<title>주문 내역</title>
<script type="text/javascript">
	
	function fnRv(no) {
		location.href = "/reWrite?no=" + no;
	}
	
	function search(id){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		
		if( (startDate != null) && (startDate != "")){
			if((endDate != null) && (endDate != "")){
				if(startDate > endDate){
					alert("종료 날짜는 시작 날짜 이전이 될 수 없습니다");
				}
				else{
					location.href = "/mypage/history/period?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
				}
			}
			else{
				alert("종료 날짜를 선택해주세요");	
			}			
		}
		else{
			alert("시작 날짜를 선택해주세요");
		}
	}
</script>
</head>
<body>
<sec:authentication property="principal" var="id"/>
   <!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/inc/header.jsp" />
   <!-- 헤더 -->
	<div class="titbox">
		<div class="title">
			<span class="name">ORDER LIST</span>
		</div>
	</div>
    <div class="article_wrap" style="width: 900px;">
    	<input type="date" name="startDate" id="startDate" min="2010-01-01" style="height: 30px">&nbsp;&nbsp;<b>~</b>&nbsp;&nbsp;
		<input type="date" name="endDate" id="endDate" min="2010-01-01" style="height: 30px"> &nbsp;&nbsp;
		<input type="button" value="조회하기" class="board_btn01" onclick="search('${id}')" style="height: 30px;">
		<div class="BLOCK30"></div>
		<table id="cartBuy">
			<tr>
				<th width="100">구매날짜</th>
				<th width="120">이미지</th>
				<th width="130">상품명</th>
				<th width="100">상품금액</th>
				<th width="70">수량</th>
				<th width="110">총금액</th>
				<th width="80">상품리뷰</th>
			</tr>
		<c:choose>
		<c:when test="${map.count == 0}">
			<tr>
				<td colspan="7">구매내역이 없습니다.</td>
       		</tr>
       	</c:when>
		<c:otherwise>
		<c:forEach var="row" items="${map.list}" varStatus="i">
			<tr>
				<td>
					<fmt:formatDate value="${row.orderDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="/productView?no=${row.no}&smallCategory=${row.smallcategory}">
						<img src="${row.imagepath}" width="120" height="120">
					</a>
				</td>
				<td style="text-align: left;">
					<a href="/productView?no=${row.no}&smallCategory=${row.smallcategory}" style="color: black;"> 
					${row.productname}</a>
				</td>
				<td>
					<fmt:formatNumber pattern="###,###,###" value="${row.price}"/> 원
				</td>
				<td>
					${row.orderCount} 개
				</td>
				<td>
					<fmt:formatNumber pattern="###,###,###" value="${row.price*row.orderCount}"/> 원
				</td>
				<td>
					<jsp:include page="/mypage/historyButton?no=${row.prodNo}" />
				</td>
			</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
	</table>
	</div>
	<div class="BLOCK20"></div>
   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- 푸터 -->
</body>
</html>