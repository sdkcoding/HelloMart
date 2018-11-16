<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<style type="text/css">
   .msg{font-size: 10pt;  color: red;}
</style>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>
<!-- 헤더 -->
<div class="article_wrap">
<div class="titbox">
	<div class="title">
		<span class="name">Q&amp;A MODIFY</span>
	</div>
</div>
<section id="bo_w">
    <c:if test="${check == 1 }">
       <script type="text/javascript">
        (function(){
          alert("글 작성 폼에 맞지 않습니다.");
           })()
      </script>
    </c:if>
    
    <form:form action="/qaboard/qamodify" method="post" modelAttribute="qaboard" id="write_form">
    <sec:authentication var="id" property="principal"/>
    <input type="hidden" name="idx" value="${qaboard.idx}">
    <div class="tbl_frm01 tbl_wrap">
        <table>
        <tbody>
        <tr>
            <td>
               <form:input path="id" class="frm_input required" size="10" maxlength="20" value="${qaboard.id }" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>
            	<form:input path="subject" size="80" maxlength="100" placeholder="제목" value="${qaboard.subject }" />
               <form:errors path="subject" cssClass="msg"/>
            </td>
        </tr>
        <tr>
            <td>
               <form:textarea path="content" rows="10" cols="82" placeholder="5자 이상 입력하세요" value="${qaboard.content }" />
               <form:errors path="content" cssClass="msg"/>
            </td>
        </tr>
        </tbody>
        </table>
    </div>

    <div class="btn_confirm">
        <input type="submit" value="수정" id="btn_submit" accesskey="s" class="btn_submit">
        <input type="reset" value="다시쓰기" class="btn_cancel">
    </div>
    </form:form>
</section>
</div>
<div class="BLOCK40"></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->
</body>
</html>