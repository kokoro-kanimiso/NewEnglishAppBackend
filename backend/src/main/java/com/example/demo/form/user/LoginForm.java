package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotEmpty(message = "id must not be empty")
	private String id;
	
	@NotEmpty(message = "Password must not be empty")
	private String password;

}
