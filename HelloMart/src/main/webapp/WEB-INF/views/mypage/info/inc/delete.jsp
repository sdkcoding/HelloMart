<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/mypage.css" />
<script>
function delSubmit() {
	f.submit();
}
</script>
<form:form action="/mypage/info/delete" method="post" name="f" modelAttribute="account">
	<table id="info_container">
		<tr>
			<th colspan=2>회원 탈퇴</th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<form:password path="password" maxlength="20"/>
				<form:errors path="password" class="errors"/>
			</td>
		</tr>
	</table>
	<p class="buttonlogin1">
		<span><a href="javascript:delSubmit();" class="btnlogin1">탈퇴하기</a></span>
	</p>
</form:form>