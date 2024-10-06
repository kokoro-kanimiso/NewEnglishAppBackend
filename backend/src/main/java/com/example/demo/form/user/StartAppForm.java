package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StartAppForm {
	
	@NotEmpty(message =  "userId must not be empty")
	private String userId;
	
}
