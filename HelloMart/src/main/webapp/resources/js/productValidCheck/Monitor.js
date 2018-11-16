function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var ScreenSize = $("#"+specEngNameList[0]).val();
	var Ratio= $("#"+specEngNameList[1]+" option:selected").val();
	var Resolution = $("#"+specEngNameList[2]+" option:selected").val();
	
	var regDouble = /^[0-9]+(.[0-9]{1})?$/;
	
	if(ScreenSize == '' || ScreenSize == '0.0' || ScreenSize == '0'){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else{
		if(!regDouble.test(ScreenSize)) {
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("소수 형식이 맞지 않습니다.");
			$("#"+specEngNameList[0]).val("");
		}else if(!(ScreenSize.length <= 5)){
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[0]).val("");
		}else{
			var doubleScreenSize = parseFloat(ScreenSize);
			if(!((0.0 <= doubleScreenSize) && (doubleScreenSize <= 180.0))){
				result = 1;
				$("#" + specEngNameList[0] + "Errors").html("값 범위가 맞지 않습니다.");
				$("#"+specEngNameList[0]).val("");
			}else{
				$("#" + specEngNameList[0] + "Errors").html("");
			}
		}
	}
	
	if(Ratio == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	
	if(Resolution == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	
	return result;
}