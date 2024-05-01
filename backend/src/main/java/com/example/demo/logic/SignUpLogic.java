package com.example.demo.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.user.SignUpController;
import com.example.demo.dao.SignUpDao;
import com.example.demo.form.SignUpForm;

@Service
public class SignUpLogic {
	private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	SignUpDao dao;
	
	@Transactional
	public String execute(SignUpForm form) {
		log.info("SignUp execute start");
		dao.registerUserInfo(form);
		log.info("SignUp execute end");
		return "execute";
	}
}
