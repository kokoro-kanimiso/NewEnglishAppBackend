package com.example.demo.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.example.demo.dto.DynamoDBTableNameEnum;

@Repository
public class StartAppDao {
	
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	
	public List<Map<String, AttributeValue>> select(String userId) {
		
		// パーティションキーを設定する（文字列型と仮定）
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
		expressionAttributeValues.put(":userIdValue", new AttributeValue().withS(userId)); // パーティションキーの型に応じて変更

		// クエリリクエストを作成
		QueryRequest queryRequest = new QueryRequest()
		        .withTableName(DynamoDBTableNameEnum.WORD.getLabel()) // テーブル名を指定
		        .withKeyConditionExpression("UserId = :userIdValue") // パーティションキーを基準にクエリ
		        .withExpressionAttributeValues(expressionAttributeValues);

		// クエリを実行
		QueryResult queryResult = amazonDynamoDB.query(queryRequest);

        // 取得した結果を返す
        return queryResult.getItems();
		
	}

}
