package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Point {
	private int idx;
	private String id;
	private String incDec;   // +,-
	private int point;
	private Date date;
	private String content;
}
