package com.example.demo.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PastOrPresentDateValidator implements ConstraintValidator<PastOrPresentDate, String> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public void initialize(PastOrPresentDate constraintAnnotation) {
        dateFormat.setLenient(false); // 厳密に日付をチェックするために設定
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // NotEmptyアノテーションで既にチェックされているはず
        }
        try {
            Date date = dateFormat.parse(value);
            return !date.after(new Date()); // 未来の日付でないことを確認
        } catch (Exception e) {
            return false; // 日付として無効な場合はバリデーションエラー
        }
    }
}
