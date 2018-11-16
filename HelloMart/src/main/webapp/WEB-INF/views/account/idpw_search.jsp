<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디/비밀번호 찾기</title>
<style>
input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
	border: none;
    border-radius: 4px;
    cursor: pointer;
}
input[type=submit]:hover {
    background-color: #45a049;
}
div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>
<script>
function submit_close() {
	if('${param.submit}' == 1) {
		window.close();
	}
}
</script>
</head>
<body onload="submit_close()">
<h3>ID/PW 찾기</h3>

<div>
  <form action="/idpw_search" method="post">
    <label for="email">email</label>
    <input type="text" id="email" name="email" placeholder="이메일 주소를 입력하여 주십시오.">
    <input type="submit" value="Submit">
  </form>
</div>
</body>
</html> 