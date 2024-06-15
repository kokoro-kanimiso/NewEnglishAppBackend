package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotEmpty(message = "Name must not be empty")
	private String name;
	
	@NotEmpty(message = "Password must not be empty")
	private String password;

}
