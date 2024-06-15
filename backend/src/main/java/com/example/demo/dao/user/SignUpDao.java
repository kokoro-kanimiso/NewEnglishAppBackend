package com.example.demo.dao.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpDao {
	
	private static final Logger log = LoggerFactory.getLogger(SignUpDao.class);
	
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SignUpDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
	
	
	/**
	 * 新規登録のデータをDBに登録
	 * @param form
	 * @return
	 */
	public int registerUserInfo(String name, String password, String birthday){
		log.info("registerUserInfo start: name={}, pass={}, birthday={}", name, password, birthday);
		String sql = "INSERT INTO  MEMBER_AUTH_INFO (name, password, birthday) VALUES (?, ?, ? )";
		return jdbcTemplate.update(sql, name, password, birthday);
	}
}
