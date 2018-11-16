package com.hellomart.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ReView {
	 private int Idx;
	 private int No;
	 private String content;
	 private String Id;
	 private Timestamp regdate;
	 private int star;
}
