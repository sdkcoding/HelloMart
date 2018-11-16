$(document).ready(function(){

	$('.header_menu_item').each(function() {
	
		$(this).on('mouseover', function() {
	
			$('.header_menu_item').removeClass('on');
	
			$('.megamenu_menu_title2').removeClass('on');
			var cd_key = $(this).attr('cd_key');
			$('.mega_menu_inner_menu').hide();
			$('.mega_menu_wrap').show();
			$('.megamenu_img_class').css({
				display : 'none'
			});
			$('.mega_' + cd_key).css({
				display : 'block'
			});
	
			$('.megamenu_menu_title2').attr({
				menu : cd_key
			});
	
			var menu = $('#category_key').val();
			$('#headeron').val(cd_key);
	
			if (cd_key == menu) {
				$('.megamenu_menu_title2.list_all').addClass('on');
			}
			var txt_title = $('#product_name').val();
			$('.megamenu_menu_title2').each(function() {
				var text = $(this).text();
				if (txt_title == text) {
					$(this).addClass('on');
				}
			});
			$('.megamenu_menu_title2_1').each(function() {
				var text = $(this).text();
				if (txt_title == text) {
					$(this).addClass('on');
				}
			});
		});
	});
	
	$('.mega_menu_wrap').on('mouseleave', function() {
		var _this = this;
		$('.header_menu_wrap').on('mouseleave', function() {
			$(_this).hide();
			$('.header_menu_item').each(function() {
				var cd_key = $(this).attr('cd_key');
				if ($('#category_key').val() == cd_key) {
					$('.header_menu_item').removeClass('on');
					$(this).addClass('on');
				}
			});
			if (!$('#category_key').val()) {
				$('.header_menu_item').removeClass('on');
			}
		});
	
	});

});