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

import com.example.demo.form.user.RegWordForm;
import com.example.demo.logic.user.RegWordLogic;

@RestController
@CrossOrigin
@RequestMapping("api")
public class RegWordController {

	private static final Logger log = LoggerFactory.getLogger(RegWordController.class);

	@Autowired
	RegWordLogic logic;

	@PostMapping("regWord")
	public ResponseEntity<?> register(@Validated @RequestBody RegWordForm form, BindingResult result) {
		log.info("RegWord Start");
		if (result.hasErrors()) {
			result.getAllErrors().forEach((error) -> {
				log.warn(error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body("Invalid Parameter Included");
		}
		try {
			logic.execute(form);
			log.info("RegWord End");
			return ResponseEntity.ok("register word successed");

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("システムエラーが発生しました");
		}
	}
}
