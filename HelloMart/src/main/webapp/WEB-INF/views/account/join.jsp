<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>
<script src="/resources/js/birthdatemaker.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/join.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript">
$(document).ready(function(){
	/* 생년월일 option 태그를 자동으로 생성해준다. */
	BIRTHDATEMAKER.make({
		year: 'year',
		month: 'month',
		day: 'day',
		begin: 1930,
		end: 2018,
		selectedYear: '${birthdate.selectedYear}',
		selectedMonth: '${birthdate.selectedMonth}',
		selectedDay: '${birthdate.selectedDay}'
	});
	/* 비밀번호 값을 볼 수 있게 해주는 toggle 버튼에 이벤트를 생성한다. */
	$(".toggle-password").click(function() {
		  $(this).toggleClass("fa-eye fa-eye-slash");
		  var input = $($(this).attr("toggle"));
		  if (input.attr("type") == "password") {
		    input.attr("type", "text");
		  } else {
		    input.attr("type", "password");
		  }
	});
	
	/* 휴대폰번호에는 숫자값만 입력가능하다. */
	$("#phone").on("keydown keyup", function(evt) {
		if(!(	
				(evt.which >= 48 && evt.which <= 57) || // 상단 숫자키패드 0-9
				(evt.which >= 96 && evt.which <= 105) ||// 우측 숫자키패드 0-9
				(evt.which == 37 || evt.which == 39) || // 좌우 방향키
				(evt.which == 8))) { // 백스페이스
			return false;
		}
	});
}); 
</script>
</head>
<body>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="titbox">
	<div class="title">
		<span class="name">MEMBER JOIN</span>
	</div>
</div>
<div id="join_test">
	<form:form action="join" name="f" method="post" modelAttribute="account" id="join_form">
		<div class="box-wrap">
			<ul class="join-form">
				<li>
					<form:input path="id" class="txt-input joinTooltip" maxlength="20" placeholder="아이디" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.id"/></span>
					<form:errors path="id" class="errors"/>
				</li>
				<li>
					<form:password path="password" value="${account.password}" class="txt-input joinTooltip" maxlength="16" placeholder="비밀번호" id="password"/>
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.password"/></span>
					<form:errors path="password" class="errors"/>
					<div id="pwCheck">
						<span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
					</div>
				</li>
				<li>
					<form:password path="re_password" class="txt-input" maxlength="16" placeholder="비밀번호 확인"/>
					<form:errors path="re_password" class="errors"/>
				</li>
				<li>
					<form:input path="name" class="txt-input joinTooltip" maxlength="20" placeholder="이름" />
						<span class="tooltiptext"><spring:message code="form.tooltip.validation.name"/></span>
					<form:errors path="name" class="errors"/>
				</li>
				<li>
					<form:input path="email" class="txt-input" maxlength="100" placeholder="이메일" />
					<form:errors path="email" class="errors"/>
				</li>
				<li class="birth">
					<dl class="type1">
						<dd>
							<form:select path="birthYear" id="year">
							</form:select>
						</dd>
						<dd>
							<form:select path="birthMonth" id="month">
							</form:select>
						</dd>
						<dd>
							<form:select path="birthDay" id="day">
							</form:select>
						</dd>
					</dl>
					<form:errors path="birthYear" class="errors"/>
				</li>
				<li class="gender">&nbsp;
					<form:radiobutton path="gender" class="input-radio" value="F" label="남"/>
					<form:radiobutton path="gender" class="input-radio" value="M" label="여" checked="checked"/>
					<form:errors path="gender" class="errors"/>
				</li>
				<li>
					<form:input path="phone" class="txt-input" maxlength="11" placeholder="휴대폰번호" id="phone" style="ime-mode:disabled;"/>
					<form:errors path="phone" class="errors"/>
				</li>
				<li>
					<form:input path="postCode" id="sample6_postcode" placeholder="우편번호" readonly="true"/>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					<form:errors path="postCode" class="errors"/><br>
				</li>
				<li>
					<form:input path="roadAddress" id="sample6_address" placeholder="주소" readonly="true"/>
					<form:input path="detailAddress" id="sample6_address2" placeholder="상세주소"/>
					<form:errors path="roadAddress" class="errors"/>
					<form:errors path="detailAddress" class="errors"/>
				</li>
			</ul>
			<div class="new-btn-area">
				<input type="submit" value="가입완료" class="btn01">
			</div>
		</div>
	</form:form>
</div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>