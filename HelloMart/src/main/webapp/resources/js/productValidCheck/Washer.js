function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var WashingCapacity = $("#"+specEngNameList[0]).val();
	var EnergyEfficiency= $("#"+specEngNameList[1]+" option:selected").val();
	var WashingType= $("#"+specEngNameList[2]+" option:selected").val();
	
	var regNumber = /^[0-9]*$/;
	
	if(WashingCapacity == '' || WashingCapacity == '0'){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(WashingCapacity)) {
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[0]).val("");
		}else if(!(WashingCapacity.length == 2)){
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[0]).val("");
		}else{
			var number = WashingCapacity.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[0] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[0]).val("");
			}else{
				var intWashingCapacity = Number(WashingCapacity);
				if((!((14 <= intWashingCapacity) && (intWashingCapacity <= 17))) && (intWashingCapacity != 19)){
					result = 1;
					$("#" + specEngNameList[0] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[0]).val("");
				}else{
					$("#" + specEngNameList[0] + "Errors").html("");
				}
			}
		}
	}
	
	if(EnergyEfficiency == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	if(WashingType == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	
	
	return result;
}