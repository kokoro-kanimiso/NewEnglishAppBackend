package com.example.demo.dto;

import lombok.Data;

@Data
public class StartAppRegWordDto {
	
	private String userId;
	private String word;
	private String correct;
	private String incorrect1;
	private String incorrect2;
	private String incorrect3;

}
