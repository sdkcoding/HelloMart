<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<link rel="stylesheet" type="text/css" href="/resources/css/common.css"/>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->
<script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<link href='/resources/bootstrap/css/bootstrap.min.css' rel="stylesheet" type="text/css">
<!-- 부트스트랩 추가로 기존의 css 파일이 뒤틀려져 보이기 때문에 모든 페이지에 부트스트랩을 추가시켜 똑같이 보이도록 하였다. -->
<script type="text/javascript">
$(document).ready(function(){
	
	$('#logo').on('click', function(){
		location.href="/";
	});
	$('#bar_menu1').on('click', function(){
		location.href="/todayView";
	});
	$('#bar_menu2').on('click', function(){
		location.href="/join";
	});
	$('#bar_menu3').on('click', function(){
		location.href="/login";
	});
	$('#bar_menu4').on('click', function(){
		location.href="/logout";
	});
	$('#bar_menu5').on('click', function(){
		location.href="/mypage/menu";
	});
	$('#bar_menu6').on('click', function(){
		location.href="/seller/page/1";
	});
	$('#bar_menu7').on('click', function(){
		location.href="/admin/page/1";
	});
	$('#bar_menu8').on('click', function(){
		location.href="/mypage/cartlist";
	});
});
</script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
.header_bar_wrap .header_bar div{ font-family: 'Hanna', serif;}
</style>
<div class="header_bar_wrap">
	<div class="header_bar">
		<div class="F_right haeder_bar_menu" id="bar_menu1">오늘 본 상품</div>
		<div class="F_right haeder_bar_menu_line"></div>
		
		<sec:authorize access="isAnonymous()">
			<div class="F_right haeder_bar_menu" id="bar_menu2">회원가입</div>
			<div class="F_right haeder_bar_menu_line"></div>
			<div class="F_right haeder_bar_menu" id="bar_menu3">로그인</div>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_SELLER')">
			<div class="F_right haeder_bar_menu" id="bar_menu8">장바구니</div>
			<div class="F_right haeder_bar_menu_line"></div>
			<div class="F_right haeder_bar_menu" id="bar_menu4">로그아웃</div>
			<div class="F_right haeder_bar_menu_line"></div>
			<div class="F_right haeder_bar_menu" id="bar_menu5">My Page</div>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ROLE_SELLER')">
			<div class="F_right haeder_bar_menu_line"></div>
			<div class="F_right haeder_bar_menu" id="bar_menu6">판매자 페이지</div>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="F_right haeder_bar_menu" id="bar_menu4">로그아웃</div>
			<div class="F_right haeder_bar_menu_line"></div>
			<div class="F_right haeder_bar_menu" id="bar_menu7">관리자페이지</div>
		</sec:authorize>
		
		<div class="clear"></div>
	</div>
</div>

<div class="header_bg_wrap A_center">
	<div class="BLOCK20"></div>
	<div class="logo_wrap">
		<h1 id="logo"><a href="/"><img src="/resources/images/logo1.png" style="margin-top: -45px; width: 250px;"></a></h1>
	</div>
</div>