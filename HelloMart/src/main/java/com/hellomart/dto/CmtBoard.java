package com.hellomart.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class CmtBoard {
	
	@Id
	private int cmtidx;
	private String id;
	private Date date;
	private int cmtpar;
	@Size(min=5, max=300, message="코멘트는 5자 이상 300자 이하로 입력하셔야 합니다.")
	private String content;
}