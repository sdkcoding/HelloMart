function productValidCheck(specEngNameList, specKorNameList){
	var result = 0;
	var ColdStorageCapacity = $("#"+specEngNameList[0]).val();
	var FreezeCapacity = $("#"+specEngNameList[1]).val();
	var DoorNumber = $("#"+specEngNameList[2]+" option:selected").val();
	var PowerConsumption = $("#"+specEngNameList[3]).val();
	var EnergyEfficiency = $("#"+specEngNameList[4]+" option:selected").val();
	
	var regNumber = /^[0-9]*$/;
	var regDouble = /^[0-9]+(.[0-9]{1})?$/;
	
	if(ColdStorageCapacity == '' || ColdStorageCapacity == '0'){
		result = 1;
		$("#" + specEngNameList[0] + "Errors").html(specKorNameList[0] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(ColdStorageCapacity)) {
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[0]).val("");
		}else if((!(ColdStorageCapacity.length == 3)) && (!(ColdStorageCapacity.length == 4))){
			result = 1;
			$("#" + specEngNameList[0] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[0]).val("");
		}else{
			var number = ColdStorageCapacity.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[0] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[0]).val("");
			}else{
				var intColdStorageCapacity = Number(ColdStorageCapacity);
				if(!((201 <= intColdStorageCapacity) && (intColdStorageCapacity <= 1000))){
					result = 1;
					$("#" + specEngNameList[0] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[0]).val("");
				}else{
					$("#" + specEngNameList[0] + "Errors").html("");
				}
			}
		}
	}
	
	if(FreezeCapacity == '' || FreezeCapacity == '0'){
		result = 1;
		$("#" + specEngNameList[1] + "Errors").html(specKorNameList[1] +"를 입력하시지 않았습니다.");
	}else{
		if(!regNumber.test(FreezeCapacity)) {
			result = 1;
			$("#" + specEngNameList[1] + "Errors").html("숫자값만 입력하세요.");
			$("#"+specEngNameList[1]).val("");
		}else if(!(FreezeCapacity.length == 3)){
			result = 1;
			$("#" + specEngNameList[1] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[1]).val("");
		}else{
			var number = FreezeCapacity.substring(0,1);
			if(number == '0'){
				result = 1;
				$("#" + specEngNameList[1] + "Errors").html("값 형식이 맞지 않습니다.");
				$("#"+specEngNameList[1]).val("");
			}else{
				var intFreezeCapacity = Number(FreezeCapacity);
				if(!((201 <= intFreezeCapacity) && (intFreezeCapacity <= 700))){
					result = 1;
					$("#" + specEngNameList[1] + "Errors").html("값 범위가 맞지 않습니다.");
					$("#"+specEngNameList[1]).val("");
				}else{
					$("#" + specEngNameList[1] + "Errors").html("");
				}
			}
		}
	}
	
	if(DoorNumber == ''){
		result = 1;
		$("#" + specEngNameList[2] + "Errors").html(specKorNameList[2] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[2] + "Errors").html("");
	}
	
	if(PowerConsumption == '' || PowerConsumption == '0.0' || PowerConsumption == '0'){
		result = 1;
		$("#" + specEngNameList[3] + "Errors").html(specKorNameList[3] +"를 입력하시지 않았습니다.");
	}else{
		if(!regDouble.test(PowerConsumption)) {
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("소수 형식이 맞지 않습니다.");
			$("#"+specEngNameList[3]).val("");
		}else if(!(PowerConsumption.length == 4)){
			result = 1;
			$("#" + specEngNameList[3] + "Errors").html("자리수가 맞지 않습니다.");
			$("#"+specEngNameList[3]).val("");
		}else{
			var doublePowerConsumption = parseFloat(PowerConsumption);
			if(!((20.0 <= doublePowerConsumption) && (doublePowerConsumption <= 31.9))){
				result = 1;
				$("#" + specEngNameList[3] + "Errors").html("값 범위가 맞지 않습니다.");
				$("#"+specEngNameList[3]).val("");
			}else{
				$("#" + specEngNameList[3] + "Errors").html("");
			}
		}
	}
	
	if(EnergyEfficiency == ''){
		result = 1;
		$("#" + specEngNameList[4] + "Errors").html(specKorNameList[4] +"를 선택하시지 않았습니다.");
	}else{
		$("#" + specEngNameList[4] + "Errors").html("");
	}
	
    return result;
}
