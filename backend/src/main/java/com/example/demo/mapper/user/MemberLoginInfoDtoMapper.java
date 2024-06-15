package com.example.demo.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.dto.MemberLoginInfoDto;

public class MemberLoginInfoDtoMapper implements RowMapper<MemberLoginInfoDto> {
	
	@Override
	public MemberLoginInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException{
		MemberLoginInfoDto dto = new MemberLoginInfoDto();
		dto.setName(rs.getString("name"));
		dto.setPassword(rs.getString("password"));
		return dto;
	}

}
