package com.example.demo.form.user;

import com.example.demo.validation.PastOrPresentDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {
	
	@NotEmpty(message = "Name must not be empty")
	@Size(max = 10, message = "Name must be less than 10 characters")
	private String name;
	
	@NotEmpty(message = "Password must not be empty")
	@Size(min = 8, max = 8, message = "Password must be exactly 8 characters")
	private String password;
	
	@NotEmpty(message = "Birthday must not be empty")
	@PastOrPresentDate
	private String birthday;

}
