var BIRTHDATEMAKER = function() {
	var attr = {
		year : undefined,
		month : undefined,
		day : undefined,
		begin : 0,
		end : 0,
		yearDefault : '년',
		monthDefault : '월',
		dayDefault : '일',
		yearDefaultAttr : '',
		monthDefaultAttr : '',
		dayDefaultAttr : '',
		selectedYear : undefined,
		selectedMonth : undefined,
		selectedDay : undefined
	}
	var dayOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var appendOption = function(id, value, valueAttr) {
		$('#' + id).append('<option value=' + valueAttr + '>' + value + '</option>');
	}
	var list = function(id, n, m) {
		for(i=n; i<=m; i++){
			appendOption(id, i, i);
		}
	}
	var listWithTitle = function(id, value, valueAttr, n, m) {
		appendOption(id, value, valueAttr);
		list(id, n, m);
	}
	var getSelectedVal = function(id) {
		return $('#' + id + ' option:selected').attr("value");
	}
	var dayEmpty = function() {
		$('#' + attr.day).empty();
	}
 	var registerEvent = function(x, y, m) {
 		$('#' + x).on("change", function() {
 			var a = getSelectedVal(y);
			var b = getSelectedVal(m);
			calculateDay(a, b);
		});
	};
	var isLeafYear = function(y) {
		var b = false;
		if((y % 400) == 0) {
			b = true;
		} else if((y % 4) == 0 && (y % 100) != 0) {
			b = true;
		}
		return b;
	}
	var calculateDay = function(y, m) {
		dayEmpty();
		appendOption(attr.day, attr.dayDefault, attr.dayDefaultAttr);
		
		if(y == attr.yearDefaultAttr || m == attr.monthDefaultAttr) return;
		
		var tempOfDay = dayOfMonth[m-1];
		
		var n = tempOfDay;
		
		var b = false;
		if(m == 2) {
			b = isLeafYear(y);
		}
		
		if(b) {
			n++;
		}
		
		list(attr.day, 1, n);
	}
	var selecteSelectedValue = function() {
		var isSelectedYear = !(attr.selectedYear === undefined);
		var isSelectedMonth = !(attr.selectedMonth === undefined);
		var isSelectedDay = !(attr.selectedDay === undefined);
		
		if(isSelectedYear) {
			select(attr.year, attr.selectedYear);
		}
		
		if(isSelectedMonth) {
			select(attr.month, attr.selectedMonth);
		}
		
		if(isSelectedYear && isSelectedMonth) {
			calculateDay(attr.selectedYear, attr.selectedMonth);
		}
		
		if(isSelectedDay) {
			select(attr.day, attr.selectedDay);
		}
	};
	var select = function(x, y) {
		$('#' + x).children("option[value='" + y + "']")
		.attr('selected', 'selected');
	}
	var init = function(z) {
		$.extend(attr, z);
	}
	function print() {
		for (var k in attr){
		    console.log('key is ' + k + ' value is ' + attr[k]);
		}
	}
	return {
		make : function(z) {
			init(z);
			
			appendOption(attr.day, attr.dayDefault, attr.dayDefaultAttr);
			
			listWithTitle(attr.year, attr.yearDefault, attr.yearDefaultAttr, attr.begin, attr.end);
			listWithTitle(attr.month, attr.monthDefault, attr.monthDefaultAttr, 1, 12);
			
			selecteSelectedValue();
			
			registerEvent(attr.year, attr.year, attr.month)
			registerEvent(attr.month, attr.year, attr.month)
		}
	};
}();