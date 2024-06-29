package com.example.demo.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.user.RegWordDao;
import com.example.demo.form.user.RegWordForm;

@Service
public class RegWordLogic {
	
	@Autowired
	RegWordDao dao;
	
	@Transactional(rollbackFor = Exception.class)
	public void execute(RegWordForm form) {
		
		String userId = form.getUserId();
		String word = form.getWord();
		String correct = form.getCorrectOption();
		String incorrect = form.getIncorrectOption();
		
		dao.register(userId, word, correct, incorrect);
	}
	
}
