package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegWordForm {
	
	@NotEmpty(message =  "userId must not be empty")
	private String userId;
	
	@NotEmpty(message =  "word must not be empty")
	private String word;
	
	@NotEmpty(message =  "correctOption must not be empty")
	private String correctOption;
	
	@NotEmpty(message =  "incorrectOption must not be empty")
	private String incorrectOption;
}
