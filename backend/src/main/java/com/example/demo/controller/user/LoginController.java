package com.example.demo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.user.LoginForm;
import com.example.demo.logic.user.LoginLogic;

@RestController
@CrossOrigin
@RequestMapping("api")
public class LoginController {
	
	@Autowired
	LoginLogic logic;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping("login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginForm form, BindingResult result) {
		log.info("Login start");
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach((error) -> {
				log.warn(error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body("Invalid Parameter Included");
		}
		try {
			Boolean res = logic.execute(form);
			if(res) {
				log.info("Login end");
				return ResponseEntity.ok("Login Success");
			}else {
				log.warn("Login fail");
				return ResponseEntity.ok("Login fail");
				}
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body("Internal Server Error");
		}
		
	}

}
