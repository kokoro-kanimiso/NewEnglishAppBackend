package com.example.demo.controller.user;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.form.user.LoginForm;
import com.example.demo.logic.user.LoginLogic;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginLogic logic;

	@Nested
	class Login {
		/**
		 * 正常系
		 */
		@Test
		void test_success() throws Exception {

			LoginForm form = new LoginForm();
			form.setName("aaa");
			form.setPassword("pass");

			when(logic.execute(form)).thenReturn(true);

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"aaa\", \"password\":\"pass\"}")).andExpect(status().isOk());

		}

		/**
		 * 異常系
		 */
		@Test
		void test_serverError() throws Exception {

			LoginForm form = new LoginForm();
			form.setName("aaa");
			form.setPassword("pass");

			doThrow(new RuntimeException("Internal Server Error")).when(logic).execute(form);

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"aaa\", \"password\":\"pass\"}"))
			.andExpect(status().isInternalServerError());

		}

	}

	@Nested
	class Validation {
		/**
		 * 異常系<br/>
		 * ・nameがnullだった場合<br/>
		 * 
		 * 期待値<br/>
		 * ・ステータスコード400を返す<br/>
		 * ・Invalid Parameter Included というメッセージが返されている
		 */
		@Test
		void test_error1() throws Exception {

			LoginForm form = new LoginForm();
			form.setName(null);
			form.setPassword("pass");

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":null, \"password\":\"pass\"}")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Invalid Parameter Included")));

		}

		/**
		 * 異常系<br/>
		 * ・passwordがnullだった場合<br/>
		 * 
		 * 期待値<br/>
		 * ・ステータスコード400を返す<br/>
		 * ・Invalid Parameter Included というメッセージが返されている
		 */
		@Test
		void test_error2() throws Exception {

			LoginForm form = new LoginForm();
			form.setName("aaa");
			form.setPassword(null);

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"aaa\", \"password\":null}")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Invalid Parameter Included")));

		}

		/**
		 * 異常系<br/>
		 * ・nameが空だった場合<br/>
		 * 
		 * 期待値<br/>
		 * ・ステータスコード400を返す<br/>
		 * ・Invalid Parameter Included というメッセージが返されている
		 */
		@Test
		void test_error3() throws Exception {

			LoginForm form = new LoginForm();
			form.setName("");
			form.setPassword("pass");

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"\", \"password\":\"pass\"}")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Invalid Parameter Included")));

		}

		/**
		 * 異常系<br/>
		 * ・passwordが空だった場合<br/>
		 * 
		 * 期待値<br/>
		 * ・ステータスコード400を返す<br/>
		 * ・Invalid Parameter Included というメッセージが返されている
		 */
		@Test
		void test_error4() throws Exception {

			LoginForm form = new LoginForm();
			form.setName("aaa");
			form.setPassword("");

			mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"aaa\", \"password\":\"\"}")).andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Invalid Parameter Included")));

		}

	}

}
