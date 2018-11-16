function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var Model = $("#"+specEngNameList[0]+" option:selected").val();
	var OS = $("#"+specEngNameList[1]+" option:selected").val();
	var Battery = $("#"+specEngNameList[2]).val();
	var LCT = $("#"+specEngNameList[3]+" option:selected").val();
	var SS = $("#"+specEngNameList[4]).val();
	
	var regNumber = /^[0-9]*$/;
	var regDouble = /^[0-9]+(.[0-9]{1})?$/;
	
	if(Model == ''){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	
	if(OS == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	if(Battery == '' || Battery == '0'){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(Battery)) {
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[2]).val("");
		}else if(!(Battery.length == 4)){
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[2]).val("");
		}else{
			var number = Battery.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[2] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[2]).val("");
			}else{
				var intBattery = Number(Battery);
				if(!((2000 <= intBattery) && (intBattery <= 8000))){
					result = 1;
					$("#" + specEngNameList[2] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[2]).val("");
				}else{
					$("#" + specEngNameList[2] + "Errors").html("");
				}
			}
		}
	}
	
	if(LCT == ''){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[3] + "Errors").html("");
	}
	
	if(SS == '' || SS == '0.0' || SS == '0'){
		result = 1;
		$("#" + specEngNameList[4] + "Errors").html(specKorNameList[4] +"를 입력하시지 않았습니다.");
	}else{
		if(!regDouble.test(SS)) {
			result = 1;
			$("#" + specEngNameList[4] + "Errors").html("소수 형식이 맞지 않습니다.");
			$("#"+specEngNameList[4]).val("");
		}else if((!(SS.length == 3)) && (!(SS.length == 4))){
			result = 1;
			$("#" + specEngNameList[4] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[4]).val("");
		}else{
			var doubleSS = parseFloat(SS);
			if((!((6.0 <= doubleSS) && (doubleSS <= 8.0))) && (!((9.0 <= doubleSS) && (doubleSS <= 14.0)))){
				result = 1;
				$("#" + specEngNameList[4] + "Errors").html("값 범위가 맞지 않습니다.");
				$("#"+specEngNameList[4]).val("");
			}else{
				$("#" + specEngNameList[4] + "Errors").html("");
			}
		}
	}
	
    return result;
}