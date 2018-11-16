<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<style type="text/css">
   .msg{font-size: 10pt;  color: red;}
   #cart_stn { margin: 0;}
   #cartBuy { 
   		margin-bottom: 20px; 
   		border: 1px solid #ababab;
   }
   tr { margin: 0 10px; }
   td { text-align: left; }
</style>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<script type="text/javascript">
function delchk(){
    if(confirm("글을 삭제하시겠습니까?")){
        location.href = "/qaboard/qadelete?idx=${view.idx}";
        return true;
    } else {
        return false;
    }
}
function cmtdelchk(cmtidx,idx){
    if(confirm("코멘트를 삭제하시겠습니까?")){
        location.href = "/qaboard/cmtdelete?cmtidx="+cmtidx+"&idx="+idx;
        return true;
    } else {
        return false;
    }
}
</script>
</head>
<body>
   <!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/inc/header.jsp" />
   <!-- 헤더 -->
	<div class="titbox">
		<div class="title">
			<span class="name">Q&amp;A VIEW</span>
		</div>
	</div>
   <div class="article_wrap">
		<table id="cartBuy">
  				<tr>
  					<th>작성자</th>
  					<td>${view.id}</td>
  					<th>작성일</th>
  					<td><fmt:formatDate value="${view.date}" pattern="yyyy.MM.dd HH:mm"/></td>
  					<th>수정일</th>
  					<td>
  						<c:if test="${view.modate ne null}">
  							<fmt:formatDate value="${view.modate}" pattern="yyyy.MM.dd HH:mm"/>
  						</c:if>
  					</td>
  				</tr>
  				<tr>
  					<th height="170px;">내용</th>
  					<td colspan="5">${view.content}</td>
  				</tr>
  		</table>
   		
		<input type="button" value="수정" id="cart_stn" onclick="location.href='/qaboard/qamodify?idx=${view.idx}'">
		<input type="button" value="삭제" id="cart_stn" onclick="delchk();">
		<input type="button" value="목록" id="cart_stn" onclick="location.href='/qaboard/qaboardList'">
   
         <!-- 코멘트 영역 -->
         <sec:authentication property="principal" var="id"/>
         <hr>
         <h4>댓글</h4>
         <table>
         <c:if test="${pageCount!=0 }">
            <c:forEach var="cmtlist" items="${cmtlist}"> 
            <tr>
               <td>${cmtlist.id}&nbsp;&nbsp;&nbsp;</td>
               <td>${cmtlist.content }</td>
               <td>
                  <fmt:formatDate value="${cmtlist.date}" pattern="yyyyMMdd HH:mm"/>
                  <a href="#" class="btn_b01" onclick="cmtdelchk('${cmtlist.cmtidx}','${view.idx }');">삭제</a>
               </td>
            </tr>
            </c:forEach>
         </c:if>
         <tr>
         <td colspan="3"><hr></td>
         </tr>
         <form:form action="cmtinsert" modelAttribute="cmtboard" method="post" id="cmt_form">
         <tr>
            <form:hidden path="cmtpar" value="${view.idx }" />
            <td>
               <form:input path="id" value="${id}" readonly="true" size="11" style="border:none;"/>
            </td>
            <td>
               <form:textarea path="content" rows="2" cols="85" placeholder="5자 이상 입력주세요" style="margin:0 10px; "/>
               <form:errors path="content" cssClass="msg"/>
            </td>
            <td align="right">
               <input type="submit" id="cart_stn" value="댓글달기">
            </td>
         </tr>
         </form:form>      
         </table>
         
         <c:if test="${pageCount>1 }">
         <div align="center" id="page">
            <c:if test="${startPage > pageBlock}">
               <a href="/qaboard/qaview?idx=${view.idx }&cmtnum=${startPage-1 }">[이전]</a>
            </c:if>         
            <c:forEach var="i" begin="${startPage }" end="${endPage }">
               <a href="/qaboard/qaview?idx=${view.idx }&cmtnum=${i }">[${i }]</a>
            </c:forEach>
            <c:if test="${endPage < pageCount }">
               <a href="/qaboard/qaview?idx=${view.idx }&cmtnum=${startPage+pageBlock }">[다음]</a>
            </c:if>
         </div>
      </c:if>
      <!-- 코멘트영역 끝 -->
      
      <!-- } 게시판 읽기 끝 -->
   </div>
   <div class="BLOCK50"></div>
   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/inc/footer.jsp" />
   <!-- 푸터 -->
</body>
</html>
