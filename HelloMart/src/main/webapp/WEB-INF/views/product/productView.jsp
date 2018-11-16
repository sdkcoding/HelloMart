<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@	taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
	function fnBuy(no, smallCategory, id){
		var orderCount = document.getElementById("orderCount").value;
		
		location.href = "/buy?no=" + no + "&smallCategory=" + smallCategory
								+ "&orderCount=" + orderCount + "&id=" + id; 
	} 
	$(function(){	
		$('.addCart').on({
			"submit" : function(){ 
			var isMove = window.confirm("장바구니 페이지로 이동하시겠습니까?");
		
			if(isMove){
				document.addCart.action = "/addCart";
				document.addCart.submit();
			}
			else{
				var d = $(this).serialize();
				
				$.ajax({
					url : "/addCartNo",
					type : "get",
					data : d,
					success : function(result){
						alert("해당 상품이 장바구니에 추가되었습니다");
					}
				});
					
				return false; // action 페이지로 전환되는 것을 차단
			}
		}
		});
	});
</script>
</head>
<body>
<sec:authentication property="principal" var="id" />
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="titbox">
	<div class="title">
		<span class="name">PRODUCT VIEW</span>
	</div>
</div>
<div align="center">
	<table>
		<c:if test="${detail == null}">
		<tr>
			<td rowspan="9">
				<img src="${product.imagePath}" width="400px">
			</td>
			<td>이름</td>
			<td>${product.productName}</td>
		</tr>
		<tr>
			<td>주문횟수</td>
			<td>${product.orderCount}</td>
		</tr>
		<tr>
			<td>상품평</td>
			<!-- 점수에 따라서 별 이미지로 처리? -->
			<td>${prodcut.score}</td> 
		</tr>
		<tr>
			<td>제작년도</td>
			<td>${product.prodDate}</td>
		</tr>
		<tr>
			<td>제작회사</td>
			<td>${product.mfCompany}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${product.price}</td>	
		</tr>
		<tr>
			<td>무게</td>
			<td>${product.weight}</td>
		</tr>
		<tr>
			<td colspan="2">판매자 코멘트 : ${product.comment}</td>
		</tr>
		</c:if>
	
		<c:if test="${detail != null}">
		<tr>
			<td rowspan="9">
				<img src="${detail.ImagePath}" width="400px">
			</td>
			<td>이름</td>
			<td>${detail.ProductName}</td>
		</tr>
		<tr>
			<td>주문횟수</td>
			<td>${detail.OrderCount}</td>
		</tr>
		<tr>
			<td>상품평</td>
			<!-- 점수에 따라서 별 이미지로 처리? -->
			<td>${detail.Score}</td> 
		</tr>
		<tr>
			<td>제작년도</td>
			<td>${detail.ProdDate}</td>
		</tr>
		<tr>
			<td>제작회사</td>
			<td>${detail.MfCompany}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${detail.Price}</td>	
		</tr>
		<tr>
			<td>무게</td>
			<td>${detail.Weight}</td>
		</tr>
		<tr>	
			<td colspan="2">
				<c:forEach items="${columnList}" varStatus="status">
					<!-- 상세정보 이름 -->	
					${columnList[status.index]} &nbsp;
					
					<!-- 상세정보 값 -->
					<c:if test="${detail[columnListEng[status.index]] == null}">ㅡ</c:if>
					${detail[columnListEng[status.index]]}
					
					<c:if test="${!status.last}"> / &nbsp; </c:if>
				</c:forEach> 
			</td>
		</tr> 
		<tr>
			<td colspan="2">판매자 코멘트 : ${detail.Comment}</td>
		</tr>
		</c:if>
	</table>                       
</div>
<br><br>
<div align="center">
	<sec:authorize access="isAnonymous()">
       	 비회원은 구매와 장바구니를 이용할 수 없습니다. 
       	 <a href="/login">로그인</a> 해주세요
    </sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_SELLER')">
<form method="get" class="addCart" name="addCart">
	수량 &nbsp;&nbsp;
	<select name="orderCount" id="orderCount">
		<c:forEach begin="1" end="10" var="i">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select> 
	<c:if test="${detail == null}">
		<input type="button" id="cart_stn" value="바로 구매" onclick="fnBuy(${product.no}, '${product.smallCategory}','${id}')">
		<input type="hidden" name="no" value="${product.no}">	
		<input type="submit" id="cart_stn" value="장바구니 담기">
	</c:if>
	<c:if test="${detail != null}">
		<input type="button" id="cart_stn" value="바로 구매" onclick="fnBuy(${detail.No}, '${detail.SmallCategory}','${id}')">
		<input type="hidden" name="no" value="${detail.No}">	
		<input type="submit" id="cart_stn" value="장바구니 담기">
	</c:if>
</form>
</sec:authorize>
</div>
<br><br><br>
		
<c:if test="${detail != null}">
	<div> 
		<jsp:include page="/review?pageNum=1&no=${detail.No}"/> 
	</div> 
</c:if>

<c:if test="${detail == null}">
	<div> 
		<jsp:include page="/review?pageNum=1&no=${product.no}"/> 
	</div> 
</c:if>

	
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>
