function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var CpuMfCompany = $("#"+specEngNameList[0]+" option:selected").val();
	var CpuKind = $("#"+specEngNameList[1]+" option:selected").val();
	var CpuCore = $("#"+specEngNameList[2]+" option:selected").val();
	var StoreageKind = $("#"+specEngNameList[3]+" option:selected").val();
	var HardCap = $("#"+specEngNameList[4]).val();
	var ScreenSize = $("#"+specEngNameList[5]).val();
	var Resolution = $("#"+specEngNameList[6]+" option:selected").val();
	
	var regNumber = /^[0-9]*$/;
	var regDouble = /^[0-9]+(.[0-9]{1})?$/;
	
	if(CpuMfCompany == ''){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[0] + "Errors").html("");
	}
	if(CpuKind == ''){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[1] + "Errors").html("");
	}
	if(CpuCore == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	if(StoreageKind == ''){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[3] + "Errors").html("");
	}
		
	if(HardCap == '' || HardCap == '0'){
		result = 1;
		$("#" + specEngNameList[4] + "Errors").html(specKorNameList[4] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(HardCap)) {
			result = 1;
			$("#" + specEngNameList[4] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[4]).val("");
		}else if(!(HardCap.length <= 4)){
			result = 1;
			$("#" + specEngNameList[4] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[4]).val("");
		}else{
			var number = HardCap.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[4] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[4]).val("");
			}else{
				var intHardCap = Number(HardCap);
				if(!((1 <= intHardCap) && (intHardCap <= 1000))){
					result = 1;
					$("#" + specEngNameList[4] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[4]).val("");
				}else{
					$("#" + specEngNameList[4] + "Errors").html("");
				}
			}
		}
	}
	
	if(ScreenSize == '' || ScreenSize == '0.0' || ScreenSize == '0'){
		result = 1;
		$("#" + specEngNameList[5] + "Errors").html(specKorNameList[5] +"를 입력하시지 않았습니다.");
	}else{
		if(!regDouble.test(ScreenSize)) {
			result = 1;
			$("#" + specEngNameList[5] + "Errors").html("소수 형식이 맞지 않습니다.");
			$("#"+specEngNameList[5]).val("");
		}else if(!(ScreenSize.length <= 4)){
			result = 1;
			$("#" + specEngNameList[5] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[5]).val("");
		}else{
			var doubleScreenSize = parseFloat(ScreenSize);
			if((!((0.0 <= doubleScreenSize) && (doubleScreenSize <= 11.6))) 
			&& (!((13.3 <= doubleScreenSize) && (doubleScreenSize <= 15.0)))
			&& (!((15.6 <= doubleScreenSize) && (doubleScreenSize <= 30.0)))){
				result = 1;
				$("#" + specEngNameList[5] + "Errors").html("값 범위가 맞지 않습니다.");
				$("#"+specEngNameList[5]).val("");
			}else{
				$("#" + specEngNameList[5] + "Errors").html("");
			}
		}
	}
	
	if(Resolution == ''){
		result = 1;
		$("#" + specEngNameList[6] + "Errors").html(specKorNameList[6] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[6] + "Errors").html("");
	}
	
    return result;
}
