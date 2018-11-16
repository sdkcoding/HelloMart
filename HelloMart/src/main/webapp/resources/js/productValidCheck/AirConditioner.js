function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var SquareMeasure = $("#"+specEngNameList[0]).val();
	var Cooling = $("#"+specEngNameList[1]).val();
	var Dehumidification = $("#"+specEngNameList[2]+" option:selected").val();
	var AirPurification = $("#"+specEngNameList[3]+" option:selected").val();
	var LowNoise = $("#"+specEngNameList[4]+" option:selected").val();
	var Type = $("#"+specEngNameList[5]+" option:selected").val();
	
	if(SquareMeasure == '' || SquareMeasure == '0.0'){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else if(!(SquareMeasure.length <= 4)){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html("자리수가 맞지 않습니다.");
		$("#"+specEngNameList[0]).val("");
	}else if((SquareMeasure != '22.8') && (SquareMeasure != '32.5')
			&& (SquareMeasure != '48.8') && (SquareMeasure !=  '52.8')
			&& (SquareMeasure != '65.9') && (SquareMeasure != '75.5')){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html("입력하신 값이 아닙니다. 6가지 실수중 하나만 입력하세요.");
		$("#"+specEngNameList[0]).val("");
	}
	else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	
	if(Cooling == '' || Cooling == '0.0'){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 입력하시지 않았습니다.");
	}else if(!(Cooling.length == 3)){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html("자리수가 맞지 않습니다.");
		$("#"+specEngNameList[1]).val("");
	}else if((Cooling != '7.5') && (Cooling != '7.7') && (Cooling != '7.8')
			&& (Cooling !=  '7.9') && (Cooling != '8.0') && (Cooling != '8.2')
			&& (Cooling != '8.5')){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html("입력하신 값이 아닙니다. 7가지 실수중 하나만 입력하세요.");
		$("#"+specEngNameList[1]).val("");
	}
	else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	if(Dehumidification == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	
	if(AirPurification == ''){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[3] + "Errors").html("");
	}
	
	if(LowNoise == ''){
		result = 1;
		$("#" + specEngNameList[4] + "Errors").html(specKorNameList[4] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[4] + "Errors").html("");
	}
	
	if(Type == ''){
		result = 1;
		$("#" + specEngNameList[5] + "Errors").html(specKorNameList[5] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[5] + "Errors").html("");
	}
    return result;
}