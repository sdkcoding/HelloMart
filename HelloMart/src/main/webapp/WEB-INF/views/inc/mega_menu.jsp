<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resources/js/dropdown.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
   function locationEncode(uri) {
      location.href=encodeURI(uri);
   }
   $("#main_menu_1").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품");
   });
   $("#main_menu_2").on('click', function(){
      locationEncode("/productList?mainCategory=IT");
   });
   $("#main_menu_3").on('click', function(){
      locationEncode("/productList?mainCategory=모바일");
   });
   $("#main_menu_4").on('click', function(){
      locationEncode("/productList?mainCategory=액세서리");
   });
   $("#main_menu_5").on('click', function(){
      locationEncode("/qaboard/qaboardList");
   });
   
   $("#refrigerator").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=냉장고");
   });
   
   $("#microwave").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=오븐_전자레인지");
   });
   
   $("#cleaner").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=청소기");
   });
   
   $("#airConditioner").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=에어컨");
   });
   
   $("#washer").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=세탁기");
   });
   
   $("#airPurifier").on('click', function(){
      locationEncode("/productList?mainCategory=가전제품&smallCategory=공기청정기");
   });
   
   $("#notebook").on('click', function(){
      locationEncode("/productList?mainCategory=IT&smallCategory=노트북");
   });
   
   $("#desktop").on('click', function(){
	  locationEncode("/productList?mainCategory=IT&smallCategory=데스크탑");
   });
   
   $("#monitor").on('click', function(){
      locationEncode("/productList?mainCategory=IT&smallCategory=모니터");
   });
   
   $("#printer").on('click', function(){
      locationEncode("/productList?mainCategory=IT&smallCategory=프린터");
   });
   
   $("#smartphone").on('click', function(){
      locationEncode("/productList?mainCategory=모바일&smallCategory=스마트폰");
   });
   
   $("#tablet").on('click', function(){
      locationEncode("/productList?mainCategory=모바일&smallCategory=태블릿");
   });
   
   $("#acc").on('click', function(){
	  locationEncode("/productList?mainCategory=액세서리&smallCategory=PC 액세서리");
   });
	
   $("#mobileAcc").on('click', function(){
	  locationEncode("/productList?mainCategory=액세서리&smallCategory=모바일 액세서리");
   });
   
   $("#qaboard").on('click', function(){
      locationEncode("/qaboard/qaboardList");
   });
});
</script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
.header_menu_wrap .header_menu div { font-family: 'Hanna', serif; }
.mega_menu_wrap .mega_menu .mega_menu_contents div { font-family: 'Jeju Gothic', sans-serif; }
</style>
<div class="header_menu_wrap">

	<div class="header_menu">
		<div class="F_left header_menu_item" id="main_menu_1" cd_key="ct_001" style="width: 167px;">가전제품</div>
		<div class="F_left header_menu_item" id="main_menu_2" cd_key="ct_002">IT</div>
		<div class="F_left header_menu_item" id="main_menu_3" cd_key="ct_003">모바일</div>
		<div class="F_left header_menu_item" id="main_menu_4" cd_key="ct_004">액세서리</div>
		<div class="F_left header_menu_item" id="main_menu_5" cd_key="ct_005">게시판</div>
		<div class="clear"></div>
	</div>

	<div class="absolute mega_menu_wrap" style="display: none;">
		<div class="mega_menu">
			<div class="mega_menu_contents">
				<div class="F_left mega_menu_contents_part2">
					<div class="mega_menu_inner_menu mega_ct_001"
						style="display: none;">
						<div class="F_left megamenu_menu_title1" id="refrigerator" style="margin-left: 50px">냉장고</div>
						<div class="F_left megamenu_menu_title1" id="microwave">오븐/전자레인지</div>
						<div class="F_left megamenu_menu_title1" id="cleaner">청소기</div>
						<div class="F_left megamenu_menu_title1" id="airConditioner">에어컨</div>
						<div class="F_left megamenu_menu_title1" id="washer">세탁기</div>
						<div class="F_left megamenu_menu_title1" id="airPurifier">공기청정기</div>
					</div>
				</div>

				<div class="mega_menu_inner_menu mega_ct_002" style="display: none;">
					<div class="F_left megamenu_menu_title2" id="notebook"
						style="margin-left: 230px">노트북</div>
					<div class="F_left megamenu_menu_title2" id="desktop">데스크탑</div>
					<div class="F_left megamenu_menu_title2" id="monitor">모니터</div>
					<div class="F_left megamenu_menu_title2" id="printer">프린터</div>
				</div>


				<div class="mega_menu_inner_menu mega_ct_003" style="display: none;">
					<div class="F_left megamenu_menu_title2" id="smartphone"
						style="margin-left: 385px">스마트폰</div>
					<div class="F_left megamenu_menu_title2" id="tablet">태블릿</div>
				</div>

				<div class="mega_menu_inner_menu mega_ct_004" style="display: none;">
					<div class="F_right megamenu_menu_title2" id="mobileAcc">모바일 액세서리</div>
					<div class="F_right megamenu_menu_title2" id="acc"
						style="margin-right: 10px;">PC 액세서리</div>
				</div>

				<div class="mega_menu_inner_menu mega_ct_005" style="display: none;">
					<div class="F_right megamenu_menu_title2" id="qaboard">Q&A 게시판</div>
				</div>
			</div>
		</div>
	</div>
</div>
