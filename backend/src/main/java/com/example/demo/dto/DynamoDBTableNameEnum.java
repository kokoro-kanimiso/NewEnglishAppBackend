package com.example.demo.dto;

public enum DynamoDBTableNameEnum {
	
	WORD("EnglishWord");

    private String label;


    private DynamoDBTableNameEnum(String label) {   //コンストラクタはprivateで宣言
        this.label = label;
    }


    public String getLabel() {
        return label;
    }


}
