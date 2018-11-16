package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Cart {
	private int idx;
	private String id;
	private int no;
	private Date date;
	private int orderCount;
	private String imagepath;
	private String smallcategory;
	private String productname;
	private String registerid;
	private int price;
}