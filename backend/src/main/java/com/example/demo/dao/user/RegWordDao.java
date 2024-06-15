package com.example.demo.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;

@Repository
public class RegWordDao {
	
	private static final Logger log = LoggerFactory.getLogger(RegWordDao.class);
	private final String TABLE_NAME = "EnglishWord";
	
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	
	public void register(String userId, String word, String correct, String incorrect) {
		log.debug("argument: userId=["+userId+"], word=["+word+"], correct=["+correct+"],incorrect=["+incorrect+"]");
		
		Map<String, AttributeValue> item = new HashMap<>();
        item.put("UserId", new AttributeValue(userId));
        item.put("Word", new AttributeValue(word));
        item.put("Correct", new AttributeValue(correct));
        item.put("Incorrect", new AttributeValue(incorrect));

        PutItemRequest putItemRequest = new PutItemRequest().withTableName(TABLE_NAME).withItem(item);
        PutItemResult result = amazonDynamoDB.putItem(putItemRequest);
        
        System.out.println(result);
	}

}
