package com.example.demo.logic.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.demo.dao.user.StartAppDao;
import com.example.demo.dto.StartAppRegWordDto;

@Service
public class StartAppLogic {
	
	@Autowired
	StartAppDao dao;
	
	public List<StartAppRegWordDto> execute(String userId) {
		
		List<Map<String, AttributeValue>> result = dao.select(userId);
		
		for(Map<String, AttributeValue> item : result) {
			System.out.println("resultGet : "+item);
		}
		
		//Dynamoから取得した登録単語情報をDTOに格納
		List<StartAppRegWordDto> regWordList = result.stream().map(this::convertItemToDto).collect(Collectors.toList());
		return regWordList;
	}
	
	private StartAppRegWordDto convertItemToDto(Map<String, AttributeValue> item) {
		StartAppRegWordDto dto = new StartAppRegWordDto();
		dto.setUserId(item.get("UserId").getS());
		dto.setWord(item.get("Word").getS());
		dto.setCorrect(item.get("Correct").getS());
		dto.setIncorrect1(item.get("Incorrect1").getS());
		dto.setIncorrect2(item.get("Incorrect2").getS());
		dto.setIncorrect3(item.get("Incorrect3").getS());
		return dto;
	}
	
}
