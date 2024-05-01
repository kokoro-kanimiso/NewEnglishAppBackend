package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.SignUpForm;

@Repository
public class SignUpDao {
	
	private static final Logger log = LoggerFactory.getLogger(SignUpDao.class);
	
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SignUpDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int registerUserInfo(SignUpForm form){
		String birthday = form.getBirthday().replace("-", "");
		String sql = "INSERT INTO  MEMBER_AUTH_INFO (name, password, birthday) VALUES (?, ?, ? )";
		return jdbcTemplate.update(sql, form.getName(), form.getPassword(), birthday);
	}
}
