<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
</head>
<body>
	<!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/inc/header.jsp" />
    <!-- 헤더 -->
	<div class="titbox">
		<div class="title">
			<span class="name">MODIFY REVIEW</span>
		</div>
	</div>
	<div class="article_wrap">
		<section id="bo_w">
			<form action="remodify" method="post">
				<sec:authentication var="id" property="principal" />
				<input type="hidden" name="idx" value="${reView.idx}">
				<div class="tbl_frm01 tbl_wrap">
					<table>
						<tbody>
							<tr>
								<td><input type="text" name="id" class="frm_input required"
									size="10" maxlength="20" value="${reView.id }"
									readonly="readonly"></td>
							</tr>
							<tr>
								<td><textarea name="content" rows="10" cols="82"
										placeholder="5자 이상 입력하세요">${reView.content }</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="btn_confirm">
					<input type="submit" value="수정하기" id="btn_submit" class="btn_submit"> 
					<input type="reset" value="다시작성" class="btn_cancel">
				</div>
			</form>
		</section>
	</div>
  	<div class="BLOCK50"></div>
  	<!-- 푸터 -->
  	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
  	<!-- 푸터 -->
</body>
</html>