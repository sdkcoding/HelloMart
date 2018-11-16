<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$.submitForm = function(f) {
		if($("#price2").val() != '' && $("#price1").val() > $("#price2").val()){
			alert("최저 가격은 최고 가격보다 적어야 합니다");
			return;
		}
		
		$(f).submit()
	}
	
	$("form").submit(function() {
		$("form").find(":input").filter(function(){return !this.value;}).attr("disabled", "disabled");
	});
	
	$.appendPage = function(page) {
		$("#detailForm").append("<input type='hidden' name='page' value='" + page + "'>");
	}
	
	$(".category_small :checkbox").on("change", function() {
		var value = $(this).attr("id");
		if($(this).is(":checked")) {
			var name = "checkedId";
			$("#detailForm").append("<input type='hidden' name='" + name + "' value='" + value + "'>");
		} else {
			console.log("#" + value);
			$("input[value=" + value + "]").remove();
		}
	});
	
	$('.addCart').on({
		"submit" : function(){ 
			var d = $(this).serialize();
		
			$.ajax({
				url : "/addCartNo",
				type : "get",
				data : d,
				success : function(result){
						alert("해당 상품이 장바구니에 1개 추가되었습니다");
					}
				});
				
				return false; // action 페이지로 전환되는 것을 차단
			}
	});
	
});
</script>
</head>

<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->

<div class="article_wrap">
<div class=BLOCK70></div>
<form action="/productList" method="get" id="detailForm"> 
<input type="hidden" name="mainCategory" value="${param.mainCategory}">
<input type="hidden" name="smallCategory" value="${param.smallCategory}">

<c:if test="${param.mainCategory == '액세서리'}"> 
	<div class="category_detail noselect" style="height: 48px;"> 
		<div class="category_detail_down" style="height: 48px;">	
			<input type="text" placeholder="제품명 검색" name="search" value="${param.search}">
			<input type="text" placeholder="0원" id="price1" name="price1" value="${param.price1}">
			<div class="range">~</div>
			<input type="text" placeholder="999,999,999원" id="price2" name="price2" value="${param.price2}">
			<div class="currency">원</div>
			<button id="submit-form" onclick="$.submitForm(this.form)" type="button"><i class="fa fa-search"></i></button>
		</div>
	</div> <!-- <div class="category_detail noselect"> -->
</c:if> <!-- test="${mainCategory == '액세서리'} -->

<c:if test="${param.mainCategory != '액세서리'}"> 
<div class="category_detail noselect">
	<div class="category_detail_up">
		<div class="category_major">
			<h5>세부 분류</h5>
			<ul>
			<c:if test="${smallCategoryList ne null}">
				<c:forEach var="smallCategory" items="${smallCategoryList}">
					<li onclick="javascript:location.href = '/productList?mainCategory=${param.mainCategory}&smallCategory=${smallCategory}'">
					<c:out value="${smallCategory}"/>
					</li>
				</c:forEach>
			</c:if>
			</ul>
		</div>
		<div class="category_small">
			<h5>상세검색</h5>
			<c:if test="${smallCategoryColumn ne null}">
				<c:forEach var="column" items="${columnList}" varStatus="status">
					<div>
						<c:out value="${column}"/><br><br>
						<c:forTokens var="value" items="${smallCategoryColumn[column]}" delims="," varStatus="innerStatus">
							<label class="ck_container">
								<c:set var="checkedId" value="${columnListEng[status.index]}${innerStatus.index}"/>
								<c:set var="id" value="${columnListEng[status.index]}${innerStatus.index}"/>
								<input type="checkbox" name="${columnListEng[status.index]}"
								id="${id}" value="${fn:trim(value)}"
								
									<c:if test="${checked ne null and checked[checkedId] ne null}">
										checked
									</c:if>
								>
								<c:if test="${checked ne null and checked[checkedId] ne null}">
									<input type="hidden" name="checkedId" value="${id}">
								</c:if>
								<span class="checkmark"></span>
								<c:out value="${fn:trim(value)}"/>
							</label>
						</c:forTokens>
					</div>
					<c:if test="${!status.last}"><hr></c:if>
				</c:forEach>
			</c:if>
		</div> <!-- <div class="category_small"> -->
	</div> <!-- <div class="category_detail_up"> -->
	<div class="category_detail_down">
		<input type="text" placeholder="제품명 검색" name="search" value="${param.search}">
		<input type="text" placeholder="0원" id="price1" name="price1" value="${param.price1}">
		<div class="range">~</div>
		<input type="text" placeholder="999,999,999원" id="price2" name="price2" value="${param.price2}">
		<div class="currency">원</div>
		<button id="submit-form" onclick="$.submitForm(this.form)" type="button"
		><i class="fa fa-search"></i></button>
	</div>
</div> <!-- <div class="category_detail noselect"> -->
</c:if> <!-- test="${mainCategory != '액세서리'} -->
</form>
<div class=BLOCK20></div>
<!-- 상품리스트 -->
<div class="product_list">
<c:choose>
	<c:when test="${list ne null}">
		<c:forEach var="board" items="${list}">
				<div class="product_list_content">
					<div class="product_img">
						<a href="/productView?no=${board.no}&smallCategory=${board.smallCategory}">
							<img src="${board.imagePath}">
						</a>
					</div>
					<div class="product_info">
						<a class="title" href="/productView?no=${board.no}&smallCategory=${board.smallCategory}">${board.productName}</a>
						<div class="additional_info">
							<span class="brand">${board.mfCompany}</span>
							<span class="category">
								<a href="/productList?mainCategory=${param.mainCategory}">${param.mainCategory}</a> > 
								<a href="/productList?mainCategory=${param.mainCategory}&smallCategory=${param.smallCategory}">${param.smallCategory}</a>
							</span>
						</div>
					</div>
					<div class="product_addition">
						<div class="price">
							<strong>${board.price} 원</strong>
						</div>
						<div class="additional_info">
							<span class="satisfaction">만족도 : ${board.score}점</span>
							<span class="buy">구  &nbsp;&nbsp;매 : ${board.orderCount}</span>  
							<span class="review">상품평 : ${board.reviewCount}개</span>
						</div>
						<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_SELLER')">
							<form method="get" class="addCart">
							<input type="submit" class="add_to_cart btn_yellow" value="장바구니 담기">	 
							<input type="hidden" name="no" value="${board.no}">	
							<input type="hidden" name="orderCount" value="1">
						</form>	
						</sec:authorize>
					</div>
				</div> <!-- <div class="product_list_content"> -->
				<hr class="style14">
		</c:forEach>
	</c:when>
	<c:otherwise>
		<h4>해당되는 상품이 없습니다.</h4>
	</c:otherwise>
</c:choose>
</div> <!-- 상품리스트 -->

</div> <!-- article_wrap 끝 -->

<br>
<div align="center">
	<c:if test="${paging.totalRecord gt 0}">
		<c:if test="${paging.nowBlock gt 0}">
			<label for="submit-form" tabindex="0" onclick="$.appendPage(${paging.beginPage - 1})">[이전]</label>
		</c:if>
		<c:forEach 	var="i"
					begin="${paging.beginPage}"
					end="${paging.endPage}">
		<label for="submit-form" tabindex="0" onclick="$.appendPage(${i})">[${i}]</label>
		</c:forEach>
		<c:if test="${paging.nowBlock lt paging.totalBlock}">
			<label for="submit-form" tabindex="0" onclick="$.appendPage(${paging.endPage + 1})">[다음]</label>
		</c:if>
	</c:if>
</div>
<br>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>