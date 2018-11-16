<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 다음 api js 파일 추가 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/daum_postcode_v6.js"></script>
<script src="/resources/jQuery/jQuery-2.1.3.min.js"></script>
<!-- 다음 api js 파일 추가 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/QABoard.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/cartTable.css" />
<title>주문 페이지</title>
<script type="text/javascript">
   $(function(){
      $('#usePoint').on({
         "click" : function(){ 
            $('#divPoint').load("/pointView?id=${account.id}");
         }
      });
      
      $('#noUsePoint').on({
         "click" : function(){ 
            $('#divPoint').empty();
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
      <span class="name">ORDER LIST</span>
   </div>
</div>
   <div class="article_wrap">
      <form action="/buyOk" method="post">
         <table id="cartBuy">
            <!-- 장바구니에 담겨있던 상품 리스트 -->
            <tr>
               <th width="180">상품이미지</th>
               <th>상품정보</th>
               <th>상품금액 x 수량</th>
               <th>총금액</th>
            </tr>
            <c:if test="${detail != null }">
               <tr>
                  <td><img src="${detail.ImagePath}" width="100"></td>            
                  <td style="text-align: left;">[ ${detail.ProductName} ] </td>
                  <td>￦&nbsp; ${detail.Price} x ${orderCount}</td>
                  <c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
                  <c:set var="totalPrice" value="${detail.Price * orderCount}" />
                  <td>￦&nbsp; ${totalPrice}</td>
               </tr>
            </c:if>
            <c:if test="${detail == null }">
               <tr>
                  <td><img src="${product.imagePath}" width="100"></td>            
                  <td style="text-align: left;">[ ${product.productName} ] </td>
                  <td>￦&nbsp; ${product.price} x ${orderCount}</td>
                  <c:set var="orderPrice" value="${productList[status.index].price * orderCountList[status.index]}" />
                  <c:set var="totalPrice" value="${product.price * orderCount}" />
                  <td>￦&nbsp; ${totalPrice}</td>
               </tr>
            </c:if>
         </table>
         <div id="total">
            <h4><b>총 금액 합계 : </b>￦&nbsp;<fmt:formatNumber pattern="###,###,###" value="${totalPrice}" /></h4>
            <div class="new-btn-area">
               <input type="submit" value="결제하기" class="btn01">
            </div>
            <div class="new-btn-area">
               <input type="button" value="취소하기" class="btn01" onclick="history.back();" style="margin: 0;" >
            </div>
         </div>   
      
         <!-- 상품 수령인 정보 -->
         <ul class="join-form">
            <li>
               <label>주문자명</label> 
               <input type="text" name="receiverName" id="hname" value="${account.name}" size="15" maxlength="30" style="margin-left: 47px;"/>
            </li>
            <li>
               <label>휴대폰번호</label>
                  <input type="text" name="receiverPhone" id="etcphone" size="15" maxlength="30" value="${account.phone}" style="margin-left: 34px;"/>
            </li>
            <li>
               <label>우편번호</label>
               <input type="text" name="receiverPostCode" id="sample6_postcode" value="${account.postCode}" readonly="readonly" style="margin-left: 48px;">
               <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
            </li>
            <li>
               <label>도로명주소</label>
               <input type="text" name="receiverRoadAddress" id="sample6_address" value="${account.roadAddress}" readonly="readonly" style="margin-left: 35px;"> 
               <input type="text" name="receiverDetailAddress" id="sample6_address2" value="${account.detailAddress}">
            </li>
            <li>
               <label>포인트 사용</label>
               <input type="radio" name="incDec" id="usePoint" value="-" style="margin-left: 32px;">&nbsp;YES&nbsp;&nbsp;
               <input type="radio" name="incDec" id="noUsePoint" value="+" checked="checked">&nbsp;NO
               <span id="divPoint"></span>
            </li>
         </ul>
      <input type="hidden" name="orderId" id="orderId" value="${account.id}">
      <input type="hidden" name="orderCount" value="${orderCount}">
      <input type="hidden" name="orderStatus" value="PAY_OK">
      <c:if test="${detail != null }">
         <input type="hidden" name="orderPrice" value="${detail.Price * orderCount}">
         <input type="hidden" name="prodNo" value="${detail.No}">
         <input type="hidden" name="prodName" value="${detail.ProductName}">   
      </c:if>
      <c:if test="${detail == null }">
         <input type="hidden" name="orderPrice" value="${product.price * orderCount}">
         <input type="hidden" name="prodNo" value="${product.no}">
         <input type="hidden" name="prodName" value="${product.productName}">      
      </c:if>
      </form>
   </div>
<div class=BLOCK60></div>
<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
<!-- 푸터 -->

</body>
</html>