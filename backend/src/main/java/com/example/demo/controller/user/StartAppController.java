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

import com.example.demo.form.user.StartAppForm;
import com.example.demo.logic.user.StartAppLogic;

@RestController
@CrossOrigin
@RequestMapping("api")
public class StartAppController {
	
	private static final Logger log = LoggerFactory.getLogger(RegWordController.class);

	@Autowired
	StartAppLogic logic;

	@PostMapping("StartApp")
	public ResponseEntity<?> start(@Validated @RequestBody StartAppForm form, BindingResult result) {
		log.info("StartApp Start");
		if (result.hasErrors()) {
			result.getAllErrors().forEach((error) -> {
				log.warn(error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body("Invalid Parameter Included");
		}
		try {
			logic.execute(form.getUserId());
			log.info("StartApp End");
			return ResponseEntity.ok(logic.execute(form.getUserId()));

		} catch (Exception e) {
			log.warn(e.getMessage());
			return ResponseEntity.internalServerError().body("システムエラーが発生しました");
		}
	}
	
}
