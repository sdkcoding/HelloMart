<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" type="text/css" href="/resources/css/mypage.css" />
<script>
function modifySubmit() {
	f.submit();
}
</script>
<form:form action="/mypage/info/modifyPwd" method="post" name="f" modelAttribute="account">
	<table id="info_container">
		<tr>
			<th colspan=2>비밀번호 변경</th>
		</tr>
		<tr>
			<th>기존 비밀번호</th>
			<td>
				<form:password path="password" maxlength="20" />
				<form:errors path="password" class="errors"/>
			</td>
		</tr>
		 <tr>
			<th>신규 비밀번호</th>
			<td>
				<form:password path="new_password" maxlength="20" class="joinTooltip"/>
				<form:errors path="new_password" class="errors"/>
				<span class="tooltiptext"><spring:message code="form.tooltip.validation.password"/></span>
			</td>
		</tr>
		<tr>
			<th>신규 비밀번호 확인</th>
			<td>
				<form:password path="re_password" maxlength="20"/>
				<form:errors path="re_password" class="errors"/>
			</td>
		</tr>
	</table>
	<p class="buttonlogin1">
		<span><a href="javascript:modifySubmit();" class="btnlogin1">수정 완료</a></span>
	</p>
</form:form>