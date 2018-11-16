<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="/resources/css/page.css">
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#deleteAccount').on('click', function() {
			var deleteList = new Array;
			var i = 0;
			$('input:checkbox[name="accountChoice"]').each(function() {
				if (this.checked) {//checked 처리된 항목의 값
					deleteList[i++] = this.value;	
				}
			});
			if(i == 0){
				alert("계정을 선택하세요.");
				return false;
			}else{
				alert("계정이 삭제되었습니다.");
				document.adminForm.accountList.value = deleteList;
				document.adminForm.action = "/admin/deleteAccount";
				document.adminForm.submit();	
			}
		});
		$('#sellerApproval').on('click', function() {
			var sellerList = new Array;
			var i = 0; var failFlag = 0;
			var sellerApply = "";
			$('input:checkbox[name="accountChoice"]').each(function() {
				if (this.checked) {//checked 처리된 항목의 값
					sellerList[i++] = this.value;
					sellerApply = $(this).next().val();
					if(sellerApply != 'SELLER_READY'){
						alert("SELLER_READY 중인 권한만 판매승인가능합니다.");
						failFlag = 1;
						return false;
					}
				}
			});
			if(failFlag != 1 && i > 0){
				alert("판매자 승인이 되었습니다.");
				document.adminForm.accountList.value = sellerList;
				document.adminForm.action = "/admin/sellerApproval";
				document.adminForm.submit();
			}else if(i == 0){
				alert("계정을 선택하세요.");
				return false;
			}
		});
		$('#searchAccount').on('click', function() {
			document.adminForm.id.value = "";
			document.adminForm.accountRole.value = "";
			document.adminForm.sellerApply.value = "";
			var id = $('#id1').val();
			var accountRole = $('#accountRole1 option:selected').val();
			var sellerApply = $('#sellerApply1 option:selected').val();
			document.adminForm.id.value = id;
			document.adminForm.accountRole.value = accountRole;
			document.adminForm.sellerApply.value = sellerApply;
			document.adminForm.action = "/admin/page/1";
			document.adminForm.submit();
		});
	});
</script>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
	<!-- 헤더 -->
	<div class="container" style="margin-top: 1%;">
	<div class="titbox">
	   <div class="title">
	      <span class="name">ADMIN PAGE</span>
	   </div>
	</div>
	<form method="post" name="adminForm">
		<table class="table table-striped table-responsive">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th colspan="4" style="text-align: center;">
						<label for="id">아이디 : <input type="text" id="id1"/></label>			
						<label for="accountRole1">권한 : 
							<select  id='accountRole1' 
								title='accountRole1' required="required">
								<option value=''>선택</option>
								<option value='ADMIN'>ADMIN</option>
								<option value='SELLER'>SELLER</option>
								<option value='MEMBER'>MEMBER</option>
							</select>
						</label>			
						<label for="sellerApply1">판매권한신청 : 
							<select  id='sellerApply1' 
								title='sellerApply1' required="required">
								<option value=''>선택</option>
								<option value='NOTHING'>NOTHING</option>
								<option value='SELLER_READY'>SELLER_READY</option>
							</select>
						</label>
						<label id="searchAccount">
							<input type="button" id="searchAccount" value="검색">
						</label>			
					</th>
				</tr>
				<tr>
					<th scope="col" style="text-align: center;">아이디</th>
					<th scope="col" style="text-align: center;">권한</th>
					<th scope="col" style="text-align: center;">판매권한신청</th>
					<th scope="col" style="text-align: center;">계정선택</th>
				</tr>
			<thead>
			<tbody>
<c:choose>	
	<c:when test="${totalCount == 0 }">
		<tr>
			<td colspan="4" style="text-align: center;">가입된 계정이 없습니다.</td>
		</tr>
	</c:when>
	<c:otherwise>		
		<c:forEach var="account" items="${accountList }">
				<tr>
					<td style="text-align: center;">${account.id }</td>
					<td style="text-align: center;">${account.role }</td>
					<td style="text-align: center;">${account.apply }</td>
					<td style="text-align: center;">
						<input type="checkbox" name="accountChoice" value="${account.id }">
						<input type="hidden" id="sellerApply2" value="${account.apply }">
					</td>
				</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
				<tr>
					<td colspan="4" style="text-align: center;">
						<div class="paginate">
							${pageCode}
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: center;">
						<span class="btn_pack">
							<input type="button" id="deleteAccount" value="계정삭제">
						</span>
						<span class="btn_pack"> 
							<input type="button" id="sellerApproval" value="판매자승인">
						</span>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name='accountList'>
		<input type="hidden" name='id'>
		<input type="hidden" name='accountRole'>
		<input type="hidden" name='sellerApply'>
	</form>
	</div>
	<div class=BLOCK60></div>
	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
	<!-- 푸터 -->
</body>
</html>