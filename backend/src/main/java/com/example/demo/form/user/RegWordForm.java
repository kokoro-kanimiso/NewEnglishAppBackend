package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegWordForm {
	
	@NotEmpty(message =  "userId must not be empty")
	private String userId;
	
	@NotEmpty(message =  "word must not be empty")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Word must be a english")
	private String word;
	
	@NotEmpty(message =  "correctOption must not be empty")
	private String correctOption;
	
	@NotEmpty(message =  "incorrectOption1 must not be empty")
	private String incorrectOption1;
	
	@NotEmpty(message =  "incorrectOption2 must not be empty")
	private String incorrectOption2;
	
	@NotEmpty(message =  "incorrectOption3 must not be empty")
	private String incorrectOption3;
	
	
}
