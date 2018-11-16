<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<title>Insert title here</title>
<style type="text/css">
table td img:HOVER {
	transform : scale(1.02) rotate(.02deg);
	transition: transform .5s cubic-bezier(.175,.885,.32,1.275);
}
</style>
</head>
<body>
<div align="center"> 
	<div class="BLOCK70"></div>
      <table align="center" width="80%" style="margin-left: 130px;">
         <c:forEach items="${list}" var="mainlist" varStatus="status">
            <c:if test="${(status.index % 4) == 0 }">
               <tr height="350">
            </c:if>
            <td>
            	<a href="/productView?no=${mainlist.no}&smallCategory=${mainlist.smallCategory}">
	               <img src="${mainlist.imagePath}" width="300" height="300" ><br>
	               [${mainlist.mainCategory}] ${mainlist.productName }<br>
	               ₩ <fmt:formatNumber pattern="###,###,###" value="${mainlist.price}" />
               </a>
            </td>
         </c:forEach>
      </table>
</div>
   <div class="BLOCK70"></div>
</body>
</html>

