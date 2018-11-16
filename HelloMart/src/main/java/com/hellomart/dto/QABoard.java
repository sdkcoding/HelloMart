package com.hellomart.dto;

import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class QABoard {

	@Id
	private int idx;
	
	@Size(min=5, max=30, message="제목은 5자 이상 30자 이하로 입력하셔야 합니다.")
	private String subject;
	
	@Size(min=5, max=500, message="내용은 5자 이상 500자 이하로 입력하셔야 합니다.")
	private String content;
	private String id;
	private int count;
	private Date date;
	private Date modate;
	private int cmt;

}