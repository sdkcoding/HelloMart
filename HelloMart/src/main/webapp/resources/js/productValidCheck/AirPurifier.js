function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var Method = $("#"+specEngNameList[0]+" option:selected").val();
	var UsableArea = $("#"+specEngNameList[1]).val();
	var FilterType = $("#"+specEngNameList[2]+" option:selected").val();
	var PowerConsumption = $("#"+specEngNameList[3]).val();
	
	var regNumber = /^[0-9]*$/;
	
	if(Method == ''){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	
	if(UsableArea == '' || UsableArea == '0'){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 입력하시지 않았습니다.");
	}else if(!regNumber.test(UsableArea)){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html("숫자값만 입력하세요.");
		$("#"+specEngNameList[1]).val("");
	}else if(!(UsableArea.length == 2)){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html("자리수가 맞지 않습니다.");
		$("#"+specEngNameList[1]).val("");
	}else if((UsableArea != '17') && (UsableArea != '33')
			&& (UsableArea != '50') && (UsableArea !=  '66')
			&& (UsableArea != '99')){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html("입력하신 값이 아닙니다. 5가지 값중 하나만 입력하세요.");
		$("#"+specEngNameList[1]).val("");
	}
	else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	if(FilterType == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	
	if(PowerConsumption == '' || PowerConsumption == '0'){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(PowerConsumption)) {
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[3]).val("");
		}else if(!(PowerConsumption.length <= 4)){
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[3]).val("");
		}else{
			var number = PowerConsumption.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[3] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[3]).val("");
			}else{
				var intPowerConsumption = Number(PowerConsumption);
				if(!((1 <= intPowerConsumption) && (intPowerConsumption <= 1000))){
					result = 1;
					$("#" + specEngNameList[3] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[3]).val("");
				}else{
					$("#" + specEngNameList[3] + "Errors").html("");
				}
			}
		}
	}
		
    return result;
}