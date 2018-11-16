function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var Method = $("#"+specEngNameList[0]+" option:selected").val();
	var Type = $("#"+specEngNameList[1]+" option:selected").val();
	var PPM = $("#"+specEngNameList[2]).val();
	var Resolution = $("#"+specEngNameList[3]+" option:selected").val();
	var Scan = $("#"+specEngNameList[4]+" option:selected").val();
	var Duplication = $("#"+specEngNameList[5]+" option:selected").val();
	var Fax = $("#"+specEngNameList[6]+" option:selected").val();
	
	var regNumber = /^[0-9]*$/;
	
	if(Method == ''){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	
	if(Type == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}

	
	if(PPM == '' || PPM == '0'){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(PPM)) {
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[2]).val("");
		}else if(!(PPM.length == 2)){
			result = 1;
			$("#" + specEngNameList[2] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[2]).val("");
		}else{
			var number = PPM.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[2] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[2]).val("");
			}else{
				var intPPM = Number(PPM);
				if(!((11 <= intPPM) && (intPPM <= 40))){
					result = 1;
					$("#" + specEngNameList[2] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[2]).val("");
				}else{
					$("#" + specEngNameList[2] + "Errors").html("");
				}
			}
		}
	}
	
	if(Resolution == ''){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[3] + "Errors").html("");
	}
		
	if(Scan == ''){
		result = 1;
		$("#" + specEngNameList[4] + "Errors").html(specKorNameList[4] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[4] + "Errors").html("");
	}
	
	if(Duplication == ''){
		result = 1;
		$("#" + specEngNameList[5] + "Errors").html(specKorNameList[5] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[5] + "Errors").html("");
	}
	
	if(Fax == ''){
		result = 1;
		$("#" + specEngNameList[6] + "Errors").html(specKorNameList[6] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[6] + "Errors").html("");
	}
	
    return result;
}
