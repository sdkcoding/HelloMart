<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="/resources/css/product.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var domestic_appliances = ["냉장고","오븐_전자레인지","청소기", "에어컨", "세탁기", "공기청정기"];
	var IT = ["노트북","데스크탑","모니터","프린터"];
	var mobile = ["스마트폰","태블릿"];
	
	$('#productRegister').on('click', function() {
		var smallValue = $('#smallCategoryInput').val();
		var mainValue = $("#mainCategoryInput").val();
		if(smallValue == ''){
			alert("하위 카테고리 입력!!");
			$('#smallCategoryInput').focus();
			return false;
		}
		if(mainValue == ''){
			alert("상위 카테고리 입력!!");
			$("#mainCategoryInput").focus();
			return false;
		}
		document.productRegisterForm.action = "/seller/productRegister";
		document.productRegisterForm.submit();
	});
	
	$("#mainCategoryInput").on('change',function(){
        var mainCategoryInputItem = $(this).val();
        /* alert($(this).children("option:selected").text()); */
        var changeItem;
        
        if(mainCategoryInputItem == "가전제품"){
      	  changeItem = domestic_appliances;
      	}
      	else if(mainCategoryInputItem == "IT"){
      	  changeItem = IT;
      	}
      	else if(mainCategoryInputItem == "모바일"){
      	  changeItem = mobile;
      	}
        
    	$('#smallCategoryInput').empty();
   	 
    	for(var count = 0; count < changeItem.length; count++){                
        	var option = $("<option value='"+changeItem[count]+"'>"+changeItem[count]+"</option>");
            $('#smallCategoryInput').append(option);
    	}
	});
});
</script>

</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->
	<div class="article_wrap">
		
		<div class=BLOCK20></div>
		<h3>${id }님의 판매 품목 목록입니다.</h3>
		<div class=BLOCK20></div>
		<div class="product_list">
<c:choose>
	<c:when test="${totalCount == 0}">
        <h3>등록된 판매 품목이 없습니다.</h3>
   	</c:when>
		    
    <c:otherwise>
		<c:forEach var="map" items="${mapList}">
		<div class="product_list_content">
			<div class="product_img">
				<a href="/productView?no=${map.no}&smallCategory=${map.SmallCategory}"><img
					src="${map.imagePath}"></a>
			</div>
			<div class="product_info">
				<a class="title" href="/productView?no=${map.no}&smallCategory=${map.SmallCategory}"> ${map.ProductName}</a>
				<div class="additional_info">
					<span class="brand">[${map.MfCompany}]</span> <span class="category"> <a
						href="#">${map.MainCategory}</a> > <a href="#">${map.SmallCategory}</a></span>
				</div>
			</div>
			<div class="product_addition">
				<div class="price">
					<strong><fmt:formatNumber pattern="###,###,###" value="${map.Price}"/>원</strong>
				</div>
				<div class="additional_info">
					<span class="satisfaction">만족도 ${map.Score}%</span> <span class="buy">구
						&nbsp;&nbsp;매 ${map.OrderCount}</span> <span class="review">상품평 ${map.count}</span><br>
					<script>
						function confirmDelete(no) {
							if(confirm('물품을 삭제하시겠습니까?')) {
								location.href='/seller/delete?no=' + no;
							}
						}
					</script>
					<input type="button" class="board_btn01" onclick="confirmDelete(${map.no})" value="상품 삭제하기">
				</div>
			</div>
		</div>
		<hr class="style14">
		</c:forEach>		       
    </c:otherwise>
</c:choose>

		</div>
		<div class="BLOCK20"></div>
		<div class="paginate">
			${pageCode}
		</div>
		<div class="BLOCK20"></div>
		<div align="center">
			<form name="productRegisterForm" method="get">
				<label for="mainCategoryInput">메인카테고리 : 
					<select  id='mainCategoryInput' name='mainCategoryInput' 
						title='mainCategoryInput' required="required">
						<option value=''>선택</option>
						<option value='가전제품'>가전제품</option>
						<option value='IT'>IT</option>
						<option value='모바일'>모바일</option>
					</select>
				</label>
				<label for="smallCategoryInput">하위카테고리 : 
					<select  id='smallCategoryInput' name='smallCategoryInput' 
						title='smallCategoryInput' required="required">
						<option value=''>선택</option>
					</select>
				</label>
				<input type="button" value="물품 등록" id="productRegister">
			</form>
		</div>
		<div class="BLOCK60"></div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>