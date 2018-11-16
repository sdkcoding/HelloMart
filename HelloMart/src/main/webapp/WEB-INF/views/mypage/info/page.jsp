<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style>
.wrap {
	width: 800px;
    margin: auto;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s;
    width: 250px;
}

.sidenav a:hover {
    color: #f1f1f1;
    text-decoration: none;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    text-align: right;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>

</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="titbox" style="margin-bottom: 10px;">
	<div class="title">
		<span class="name">MEMBER</span>
	</div>
</div>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/mypage/info">회원정보 보기</a>
  <a href="/mypage/info/modify">회원정보 수정</a>
  <a href="/mypage/info/modifyPwd">비밀번호 변경</a>
  <a href="/mypage/info/delete">회원 탈퇴</a>
</div>

<div class="wrap">
	<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>
	<div class="BLOCK20"></div>
	<jsp:include page="/WEB-INF/views/mypage/info/inc/${viewPage}.jsp"/> 
</div>
<div class="BLOCK60"></div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>