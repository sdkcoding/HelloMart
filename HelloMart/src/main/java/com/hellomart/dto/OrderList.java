package com.hellomart.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class OrderList {
	@Id
	private int idx;
	
	@Size(min=8, max=20, message="아이디는 8~20자 이하로 입력하셔야 합니다")
	@Pattern(regexp="[a-zA-Z][0-9a-zA-Z]{5,19}", 
			message="아이디는 영문자로 시작해야하며, 영문자 혹은 숫자로 구성됩니다")
 	private String orderId;
	
	@Size(max=20, message="이름은 20자 이하로 입력하셔야 합니다")
 	private String receiverName;
	
 	private String receiverPhone;
	
	private int prodNo;
	
	private int orderCount;
	
	private Date orderDate;
	
	private int orderPrice;
	
	private String receiverPostCode;
	
	private String receiverRoadAddress;
	
	private String receiverDetailAddress;

	private String orderStatus;	
}

