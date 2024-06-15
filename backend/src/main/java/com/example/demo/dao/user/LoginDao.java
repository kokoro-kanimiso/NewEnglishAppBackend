package com.example.demo.dao.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberLoginInfoDto;
import com.example.demo.mapper.user.MemberLoginInfoDtoMapper;

@Repository
public class LoginDao {
	
	private static final Logger log = LoggerFactory.getLogger(LoginDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<MemberLoginInfoDto> findLoginUserInfo(String name) {
		String sql = "SELECT name,password FROM MEMBER_AUTH_INFO WHERE name = ?";
		
		return jdbcTemplate.query(sql, new MemberLoginInfoDtoMapper(),name);
	}
}
