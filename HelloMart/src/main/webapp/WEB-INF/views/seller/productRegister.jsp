<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">

<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script src="/resources/js/productValidCheck/${smallCategoryEng }.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/register.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>물품등록페이지</title>
<script type="text/javascript">
$(document).ready(function(){
	//콤마찍기
	function comma(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}
	//콤마풀기
	function uncomma(str) {
	    str = String(str);
	    return str.replace(/[^\d]+/g, '');
	}
	function inputNumberFormat() {
		var price = $("#price").val();
		$("#price").val(comma(uncomma(price)));
	}
	$("#btnRegister").on("click", function(){
		var productName = $("#productName").val();
		var mfCompany = $("#mfCompany").val();
		var prodDate = $("#prodDate").val();
		var price = $("#price").val();
		var weight = $("#weight").val();
		var comment = $("#comment").val();
		var productImageFile = $("#productImageFile").val();
		var flag = 0;
		
		var regInputText = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣 ]*$/;//영어대소문자숫자한글만입력 공백 포함
		
		if(productName == ''){
			$("#productNameError").html("필수 입력란입니다.");
			flag = 1;
		}else if(!regInputText.test(productName)){
			$("#productNameError").html("영어대소문자숫자한글만 입력하세요.");
			flag = 1;
		}else if(!((1 <= productName.length) && (productName.length <= 50))){
			$("#productNameError").html("이름길이가 맞지 않습니다.");
			flag = 1;
		}else{
			$("#productNameError").html("");
		}
		
		if(mfCompany == ''){
			$("#mfCompanyError").html("필수 입력란입니다.");
			flag = 1;
		}else if(!regInputText.test(mfCompany)){
			$("#mfCompanyError").html("영어대소문자숫자한글만 입력하세요.");
			flag = 1;
		}else if(!((1 <= mfCompany.length) && (mfCompany.length <= 30))){
			$("#mfCompanyError").html("이름길이가 맞지 않습니다.");
			flag = 1;
		}else{
			$("#mfCompanyError").html("");
		}
		
		var regDate = /^\d{4}-\d{2}-\d{2}$/;//1998-10-12
		
		var dayOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		
		var isLeafYear = function(y) {
			var b = false;
			if((y % 400) == 0) {
				b = true;
			} else if((y % 4) == 0 && (y % 100) != 0) {
				b = true;
			}
			return b;
		}
		
		var today = new Date();
		var presentDay = today.getDate();
		var presentMonth = today.getMonth()+1; //January is 0!
		var presentYear = today.getFullYear();
		
		var prodDateflag = 0;
		
		if(prodDate == ''){
			$("#prodDateError").html("필수 입력란입니다.");
			flag = 1; prodDateflag = 1;
		}else{
			if(!regDate.test(prodDate)){
				$("#prodDateError").html("날짜 형식이 맞지 않습니다.");
				flag = 1; prodDateflag = 1;
			}else{
				var prodDate = prodDate.split('-');
				var prodYear = Number(prodDate[0]);
				var prodMonth = Number(prodDate[1]);
				var prodDay = Number(prodDate[2]);
				var min = Math.min.apply(null, dayOfMonth);
				var max = Math.max.apply(null, dayOfMonth);
				if(isLeafYear(prodYear)){
					dayOfMonth[1] = dayOfMonth[1] + 1;
				}
				if(
					(prodYear > presentYear)
					|| ((prodYear == presentYear) && (prodMonth > presentMonth))
					|| ((prodYear == presentYear) && (prodMonth == presentMonth) && (prodDay > presentDay))) {
					$("#prodDateError").html("현재 날짜를 넘었습니다.");
					flag = 1; prodDateflag = 1;
				}else if(!((1930 <= prodYear) && (prodYear <= presentYear))){
					$("#prodDateError").html("1930년도 이상으로 입력해주세요.");
					flag = 1; prodDateflag = 1;
				}else if(!((1 <= prodMonth) && (prodMonth <= 12))){
					$("#prodDateError").html("월 범위가 맞지 않습니다.");
					flag = 1; prodDateflag = 1;
				}else if(!((1 <= prodDay) && (prodDay <= max))){
					$("#prodDateError").html("일 범위가 맞지 않습니다.");
					flag = 1; prodDateflag = 1;
				}else{
					for(var i = 0 ; i < dayOfMonth.length ; i++){
						if(prodMonth == i + 1){
							if(!(prodDay <= dayOfMonth[i])){
								$("#prodDateError").html("월에 해당하는 일이 아닙니다.");
								flag = 1; prodDateflag = 1;
								break;
							}
						}
					}
				}
			}
		}
		
		if(prodDateflag == 0){
			$("#prodDateError").html("");
		}
		
		var regNumber = /^[0-9]*$/; //숫자만 입력
		
		if(price == '' || price == '0'){
			$("#priceError").html("필수 입력란입니다.");
			flag = 1;
		}else if(!regNumber.test(price)){
			$("#priceError").html("값 형식이 맞지 않습니다.");
			flag = 1;
		}else if(!((3 <= price.length) && (price.length <= 20))){
			$("#priceError").html("자리수가 맞지 않습니다.");
			flag = 1;
		}else{
			$("#priceError").html("");
		}
		
		var regDouble = /^(\d{1,7}([.]\d{0,2})?)?$/; //9자리 중 자연수 7자리 소수점 뒷자리 2자리만
		
		if(weight == '' || weight == '0.0'){
			$("#weightError").html("필수 입력란입니다.");
			flag = 1;
		}else if(!regDouble.test(weight)){
			$("#weightError").html("값 형식 맞지 않습니다.");
		}else{
			$("#weightError").html("");
		}
		
		if(comment == ''){
			$("#commentError").html("필수 입력란입니다.");
			flag = 1;
		}else if(!((1 <= comment.length) && (comment.length <= 100))){
			$("#commentError").html("글자 수가 100자 넘었습니다.");
			flag = 1;
		}else{
			$("#commentError").html("");
		}
		
		if(productImageFile == ''){
			$("#fileError").html("필수 입력란입니다.");
			flag = 1;
		}else if(fileExtensionCapacityCheck('productImageFile') == 0){
			flag = 1;
		}
		else{
			$("#fileError").html("");
		}
		
		function fileExtensionCapacityCheck(string){
			var result = 1;
			var ext = $("#" + string).val().split('.').pop().toLowerCase();
		    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
		    	$("#fileError").html("gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.");
		    	$("#" + string).val("");
		    	$("#" + string).focus();
		    	result = 0;
		    }
		    var file = document.getElementById(string).files[0];
		    var maxSize = 1024*1024;
		    if(file.size > maxSize){
		    	$("#fileError").html("사진 파일용량이 1MB를 초과했습니다.");
		    	$("#" + string).val("");
		    	$("#" + string).focus();
		    	result = 0;
		    }
		    return result;
		}
		
		var specEngNameList = '${specEngNameList}';
		var specKorNameList = '${specKorNameList}';
		var specEngNameList = specEngNameList.substring(1, specEngNameList.length-1);
		var specKorNameList = specKorNameList.substring(1, specKorNameList.length-1);
		var specEngNameList = specEngNameList.split(',');
		var specKorNameList = specKorNameList.split(',');
		for(var count = 0 ; count < specEngNameList.length; count++){
			specEngNameList[count] = specEngNameList[count].trim();
			specKorNameList[count] = specKorNameList[count].trim();
		}
		
		if(productValidCheck(specEngNameList, specKorNameList)){
			flag = 1;
		}
		
		if(flag == 1){
			return false;
		}
		document.productRegister.submit();
	});
}); 
</script>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp" />
	<!-- 헤더 -->
<div class="BLOCK80"></div>
<div id="register_test">
	<form:form action="/seller/productRegister" name="productRegister" commandName="ProductList" method="post" enctype="multipart/form-data">
		<form:hidden path="mainCategory" value="${mainCategory }"/>
		<form:hidden path="smallCategory" value="${smallCategory }"/>
		<h2>${mainCategory } >> ${smallCategory } 등록하기</h2><br/>
		<div class="box-wrap">
			<ul class="register-form">
				<li>
					<label for="productName" class="control-label">물품 이름</label>	
					<form:input path="productName" id="productName" class="txt-input joinTooltip" maxlength="50" placeholder="물품 이름" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.productName"/></span>
					<span class="errors" id="productNameError"></span>
					<form:errors path="productName" class="errors"/>
				</li>
				<li>
					<label for="mfCompany" class="control-label">제조 회사</label>
					<form:input path="mfCompany" id="mfCompany" class="txt-input joinTooltip" maxlength="30" placeholder="제조 회사" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.mfCompany"/></span>
					<span class="errors" id="mfCompanyError"></span>
					<form:errors path="mfCompany" class="errors"/>
				</li>
				<li>
					<label for="prodDate" class="control-label">제조 날짜</label>
					<form:input path="prodDate" id="prodDate"  class="txt-input joinTooltip" maxlength="10" placeholder="제조 날짜"/>
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.prodDate"/></span>
					<span class="errors" id="prodDateError"></span>
					<form:errors path="prodDate" class="errors"/>
				</li>
				<li>
					<label for="price" class="control-label">물품 가격</label>
					<form:input path="price" id="price" class="txt-input joinTooltip"
						onkeyup="inputNumberFormat()" maxlength="20" placeholder="물품 가격" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.price"/></span>
						<span class="unit">원</span>
					<span class="errors" id="priceError"></span>	
					<form:errors path="price" class="errors"/>
				</li>
				<li>
					<label for="weight" class="control-label">물품 무게</label>
					<form:input path="weight" id="weight" class="txt-input joinTooltip" maxlength="10" placeholder="물품 무게" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.weight"/></span>
						<span class="unit">Kg</span>
					<span class="errors" id="weightError"></span>
					<form:errors path="weight" class="errors"/>
				</li>
				<li>
					<label for="comment" class="control-label">코멘트</label>
					<form:textarea path="comment" id="comment" class="txt-input joinTooltip" cols="50" rows="5" maxlength="100" placeholder="코멘트"/>
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.comment"/></span>
					<span class="errors" id="commentError"></span>
					<form:errors path="comment" class="errors"/>
				</li>
				<li>
					<label for="productImageFile" class="control-label">이미지 업로드</label>
						<input type="file" name="productImageFile"  id="productImageFile" class="txt-input joinTooltip productImageFile"/>
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.file"/></span>
					<span class="errors" id="fileError">${fileMsg }</span>
				</li>				
				
<c:forEach var="specKorName" items="${specKorNameList}" varStatus="status">
	<c:set var="specKorName" value="${specKorName }"/>
	<c:choose>
   		<c:when test="${specTypeList[status.index] eq 'Integer' or specTypeList[status.index] eq 'Double' }">
            	<li>
				<label for="${specEngNameList[status.index] }" class="control-label">${specKorName }</label>
			<c:choose>
				<c:when test="${specTypeList[status.index] eq 'Integer'}">
				<input type="text" name='${specEngNameList[status.index] }' id="${specEngNameList[status.index] }" 
					title='${specKorName }' class="txt-input joinTooltip" placeholder="${specKorName }" value="0"/>
				</c:when>
				<c:otherwise>
				<input type="text" name='${specEngNameList[status.index] }' id="${specEngNameList[status.index] }" 
					title='${specKorName }' class="txt-input joinTooltip" placeholder="${specKorName }" value="0.0"/>	
				</c:otherwise>
			</c:choose>
				<span class="tooltiptext">
					<spring:message code="form.tooltip.validation.${smallCategoryEng }.${specEngNameList[status.index] }"/>
				</span>
				<span class="unit">${specUnitList[status.index] }</span>
				<span class="errors" id="${specEngNameList[status.index] }Errors">
				${specKorNameError[status.index]}			
				</span>	
				</li> 
    	</c:when>
	    <c:when test="${specTypeList[status.index] eq 'String' or specTypeList[status.index] eq 'Enum' }">
	        	<li>
				<label for="${specEngNameList[status.index] }" class="control-label">${specKorName }</label>
				<select name='${specEngNameList[status.index] }' id="${specEngNameList[status.index] }" 
					title='${specKorName }리스트' class="txt-input joinTooltip" required="required">
					<option value=''>선택</option>
			<c:forEach var="value" items="${specList[status.index]}">
					<option value='${value }'>${value }</option>
			</c:forEach>
				</select>
				<span class="tooltiptext">
					<spring:message code="form.tooltip.validation.${smallCategoryEng }.${specEngNameList[status.index] }"/>
				</span>
				<span class="errors" id="${specEngNameList[status.index] }Errors">
				${specKorNameError[status.index]}			
				</span>	
				</li>   
	    </c:when>
	</c:choose>
</c:forEach>
			</ul><br>
			<div class="new-btn-area">
				<span class="btnlogin" id="btnRegister">등록 완료</span>
			</div>
		</div>
	</form:form>
</div>
		<div class=BLOCK60></div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->
</body>
</html>