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

import com.example.demo.form.SignUpForm;
import com.example.demo.logic.SignUpLogic;

@RestController
@CrossOrigin
@RequestMapping("api")
public class SignUpController {
	
	@Autowired
	SignUpLogic logic;
	
	private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
	
	
	@PostMapping("signup")
	public ResponseEntity<?> signup(@Validated @RequestBody SignUpForm form, BindingResult result) {
		log.info("login started");
		if(result.hasErrors()) {
			result.getAllErrors().forEach((error) -> {
				log.warn(error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body("Invalid Parameter Included");
		}
		log.info("Formのname: "+form.getName());
		String res = logic.execute(form);
		log.info(res);
		log.info("login end");
		return ResponseEntity.ok("SignUp Success");
	}
	
}	
