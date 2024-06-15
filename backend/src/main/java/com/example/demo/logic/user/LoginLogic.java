package com.example.demo.logic.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.user.LoginDao;
import com.example.demo.dto.MemberLoginInfoDto;
import com.example.demo.form.user.LoginForm;

@Service
public class LoginLogic {

	private static final Logger log = LoggerFactory.getLogger(LoginLogic.class);
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginDao dao;

	/**
	 * @param form リクエストフォーム
	 * @return ログイン結果の成否
	 */
	public boolean execute(LoginForm form) {
		log.info("SignUp execute start");
		

		// DB登録実行
		List<MemberLoginInfoDto> list = dao.findLoginUserInfo(form.getName());
		if(list != null) {
			if(list.size() == 1 && passwordMatches(form.getPassword(), list.get(0).getPassword())) {
				log.info("SignUp execute end");
				return true;
			}else {
				log.info("SignUp execute end");
				return false;
			}
		}
		return false;
	}
	
	public boolean passwordMatches(String enteredPassword, String registeredPassword) {
		return passwordEncoder.matches(enteredPassword, registeredPassword);
	}

}
