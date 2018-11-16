<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<title>Insert title here</title>
<script type="text/javascript">
	function fnRv(no) {
		location.href = "/reWrite?no=" + no;
	}
</script>
</head>
<body>
	<c:if test="${check == false}">
		<input type="button" class="board_btn01" value="리뷰 작성" onclick="fnRv('${no}')">
	</c:if>
	
	<c:if test="${check == true}">
		<input type="button" class="board_btn01" value="리뷰 수정" onclick="location.href='/remodify?idx=${idx}'" style="margin: 2px 5px;">
		<input type="button" class="board_btn01" value="리뷰 삭제" onclick="location.href='/redelete?idx=${idx}'" style="margin: 2px 5px;">	
	</c:if>
</body>
</html>