function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var Capacity = $("#"+specEngNameList[0]).val();
	var ControlMethod= $("#"+specEngNameList[1]+" option:selected").val();
	var PowerConsumption = $("#"+specEngNameList[2]).val();
	var Watt = $("#"+specEngNameList[3]).val();
	
	var regNumber = /^[0-9]*$/;
	
	if(Capacity == '' || Capacity == '0'){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(Capacity)) {
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[0]).val("");
		}else if(!(Capacity.length == 2)){
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[0]).val("");
		}else{
			var number = Capacity.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[0] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[0]).val("");
			}else{
				var intCapacity = Number(Capacity);
				if(!((20 <= intCapacity) && (intCapacity <= 25))){
					result = 1;
					$("#" + specEngNameList[0] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[0]).val("");
				}else{
					$("#" + specEngNameList[0] + "Errors").html("");
				}
			}
		}
	}
	
	if(ControlMethod == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	
	if(PowerConsumption == '' || PowerConsumption == '0'){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(PowerConsumption)) {
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[2]).val("");
		}else if((!(PowerConsumption.length == 4)) && (!(PowerConsumption.length == 3))){
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[2]).val("");
		}else{
			var number = PowerConsumption.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[2] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[2]).val("");
			}else{
				if((PowerConsumption != '700') && (PowerConsumption != '1050')
						&& (PowerConsumption != '1100') && (PowerConsumption !=  '1570')){
					result = 1;
					$("#" + specEngNameList[2] + "Errors").html("입력하신 값이 아닙니다. 4가지 중 하나만 입력하세요.");
					$("#"+specEngNameList[2]).val("");
				}else{
					$("#" + specEngNameList[2] + "Errors").html("");
				}
			}
		}
	}
	
	if(Watt == '' || Watt == '0'){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(Watt)) {
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[3]).val("");
		}else if((!(Watt.length == 4)) && (!(Watt.length == 3))){
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[3]).val("");
		}else{
			var number = Watt.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[3] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[3]).val("");
			}else{
				if((Watt != '700') && (Watt != '1000')){
					result = 1;
					$("#" + specEngNameList[3] + "Errors").html("입력하신 값이 아닙니다. 2가지 중 하나만 입력하세요.");
					$("#"+specEngNameList[3]).val("");
				}else{
					$("#" + specEngNameList[3] + "Errors").html("");
				}
			}
		}
	}
	
	return result;
}