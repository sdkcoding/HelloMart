function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var WireLess = $("#"+specEngNameList[0]+" option:selected").val();
	var ChargingTime = $("#"+specEngNameList[1]).val();
	
	var regNumber = /^[0-9]*$/;
	
	if(WireLess == ''){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
		
	if(ChargingTime == '' || ChargingTime == '0'){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(ChargingTime)) {
			result = 1;
			$("#" + specEngNameList[1] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[1]).val("");
		}else if(!(ChargingTime.length == 1)){
			result = 1;
			$("#" + specEngNameList[1] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[1]).val("");
		}else{
			var number = ChargingTime.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[1] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[1]).val("");
			}else{
				var intChargingTime = Number(ChargingTime);
				if(!((0 <= intChargingTime) && (intChargingTime <= 7))){
					result = 1;
					$("#" + specEngNameList[1] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[1]).val("");
				}else{
					$("#" + specEngNameList[1] + "Errors").html("");
				}
			}
		}
	}
		
    return result;
}