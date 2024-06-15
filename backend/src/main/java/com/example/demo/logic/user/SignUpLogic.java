package com.example.demo.logic.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.user.SignUpController;
import com.example.demo.dao.user.SignUpDao;
import com.example.demo.form.user.SignUpForm;

@Service
public class SignUpLogic {
	private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	SignUpDao dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	/**
	 * 新規登録のロジックメソッド
	 * @param form リクエストフォーム
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String execute(SignUpForm form) {
		log.info("SignUp execute start");
		
		//リクエストフォームに格納されたパスワードを暗号化
		String encodedPassword = encodePassword(form.getPassword());
		log.info("暗号化: "+encodedPassword);
		
		//リクエストフォームに格納された誕生日を「YYMMDD」の形に変換
		String birthday = formatBirthday(form.getBirthday());
		
		//DB登録実行
		dao.registerUserInfo(form.getName(), encodedPassword, birthday);
		log.info("SignUp execute end");
		return "execute";
	}
	
	
	/**
	 *
	 * パスワードを暗号化するメソッド
	 * 
	 * @param リクエストから渡された生のパスワード unEncodedPassword
	 * @return 暗号化されたパスワード
	 */
	public String encodePassword(String unEncodedPassword) {
		return passwordEncoder.encode(unEncodedPassword);
	}
	
	
	/**
	 * 
	 * YYYY-MM-DD形式をYYYYMMDDに変更するメソッド
	 * 
	 * @param リクエストから渡された誕生日 birthday
	 * @return YYYYMMDDのフォーマットに変更したもの
	 */
	public String formatBirthday(String birthday) {
		return birthday.replace("-", "");
	}
}
