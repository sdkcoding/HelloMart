package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data 
public class Account {
	private String id;
	private String password;
	private String new_password;
	private String re_password;
	private String role;
	private int points;
	private int grade;
	private String email;
	private String postCode;
	private String roadAddress;
	private String detailAddress;
	private String phone;
	private String name;
	private String birthYear;
	private String birthMonth;
	private String birthDay;
	private char gender;
	private int reliability;
	private Date joinDate;
	private String apply;
}
