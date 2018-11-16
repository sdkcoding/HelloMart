<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<title>적립금 내역</title>
<script type="text/javascript">
	function search(id){
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		
		if( (startDate != null) && (startDate != "")){
			if((endDate != null) && (endDate != "")){
				if(startDate > endDate){
					alert("종료 날짜는 시작날짜 이전이 될 수 없습니다");
				}
				else{
					location.href = "/mypage/point/period?id=" + id + "&startDate=" + startDate + "&endDate=" + endDate;
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
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="titbox">
	<div class="title">
		<span class="name">MY POINT</span>
	</div>
</div>
<sec:authentication property="principal" var="id"/>
	<div class="article_wrap" style="width: 900px;">
		<input type="date" name="startDate" id="startDate" min="2010-01-01" style="height: 30px">&nbsp;~&nbsp;
		<input type="date" name="endDate" id="endDate" min="2010-01-01" style="height: 30px">&nbsp;&nbsp;
		<input type="button" value="조회하기" class="board_btn01" onclick="search('${id}')" style="height: 30px;">
		<div class="BLOCK30"></div>
		<table id="cartBuy">
			<tr>
				<th width="90">날짜</th>
				<th width="80">적립</th>
				<th width="80">사용</th>
				<th width="180">내 용</th>
			</tr>	
		<c:forEach var="point" items="${pointList}">
			<tr>
				<td>
					<fmt:formatDate value="${point.date}" pattern="yyyy-MM-dd"/>
				</td>
				<c:if test="${point.incDec == '+'}">
					<td>${point.point}</td>
					<td>-</td>
				</c:if>		
				<c:if test="${point.incDec == '-'}">
					<td>-</td>
					<td>${point.point}</td>
				</c:if>
				<td style="text-align: left;">${point.content}</td>	
			</tr>
		</c:forEach>
		</table>
	</div>
<div class="BLOCK60"></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>