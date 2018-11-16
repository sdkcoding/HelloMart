<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HelloMart</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
			<span class="name">Q&A 게시판</span>
		</div>
	</div>
	<div class="article_wrap">
		<div id="bo_list">
			<div id="bo_list_total">
				전체 <span>${paging.totalRecord}</span>건
			</div>
			<div class="tbl_head01 tbl_wrap">
				<table>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">글쓴이</th>
							<th scope="col">날짜</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody>
						<sec:authentication property="principal" var="loginid" />
						<c:if test="${list != null}">
							<c:forEach var="board" items="${list}">
								<tr class="bo_notice">
									<td class="td_num">${board.idx}</td>
									<td class="td_subject"><sec:authorize
											access="isAnonymous()">
                               회원만 접근 가능합니다.
                           </sec:authorize> <sec:authorize
											access="hasAnyRole('ROLE_MEMBER', 'ROLE_SELLER')">
											<c:choose>
												<c:when test="${loginid == board.id }">
													<a href="/qaboard/qaview?idx=${board.idx }&id=${board.id}">${board.subject }</a> [${board.cmt }]
                              </c:when>
												<c:otherwise>
                               회원만 접근 가능합니다.
                               </c:otherwise>
											</c:choose>
										</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
											<a href="/qaboard/qaview?idx=${board.idx }">${board.subject }</a> [${board.cmt }]
                              </sec:authorize></td>
									<td class="td_name sv_use">${board.id }</td>
									<td class="td_date"><fmt:formatDate value="${board.date}"
											pattern="yyyy-MM-dd" /></td>
									<td class="td_num">${board.count }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<br>
			<div class="bo_fx">
				<ul class="btn_bo_user">
					<sec:authorize access="isAuthenticated()">
						<li><a href="/qaboard/qawrite" class="btn_b02">글쓰기</a></li>
					</sec:authorize>
				</ul>
			</div>

			<div align="center" style="height: 30px">
				<sec:authorize access="isAnonymous()">
               게시글을 작성 하려면 <a href="/login">로그인</a> 해 주세요.
            </sec:authorize>
			</div>
		</div>

		<div align="center">
			<c:if test="${paging.totalRecord gt 0}">
				<c:if test="${paging.nowBlock gt 0}">
					<a href="/qaboard/qaboardList?page=${paging.beginPage - 1}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${paging.beginPage}"
					end="${paging.endPage}">
					<a href="/qaboard/qaboardList?page=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${paging.nowBlock lt paging.totalBlock}">
					<a href="/qaboard/qaboardList?page=${paging.endPage + 1}">[다음]</a>
				</c:if>
			</c:if>
		</div>
		<br>

		<!-- 게시판 검색 시작 { -->
		<div align="center">
			<form action="/qaboard/qaboardList" method="get">
				<label for="sfl" class="sound_only">검색대상</label> <select
					name="searchOption" id="searchOption">
					<option value="subject">제목</option>
					<option value="content">내용</option>
					<option value="id">글쓴이</option>
				</select> <label for="stx" class="sound_only">검색어 <strong
					class="sound_only"> 필수</strong>
				</label> <input type="text" name="keyword" required id="keyword"
					class="frm_input required" size="15" maxlength="15"> <input
					type="submit" value="검색" class="btn_submit">
			</form>
		</div>
	</div>

	<!-- 게시판 검색 끝 -->
	<div style="padding-top: 50px"></div>

	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/inc/footer.jsp" />
	<!-- 푸터 -->

</body>
</html>