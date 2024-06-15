package com.example.demo.controller.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.form.user.SignUpForm;
import com.example.demo.logic.user.SignUpLogic;

@WebMvcTest(SignUpController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SignUpControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SignUpLogic logic;
	
	@Nested
	class signup{
		/**
		 * 正常系のテスト
		 */
		@Test
		void test_success1() throws Exception {
			
			SignUpForm form = new SignUpForm();
			form.setName("John");
			form.setPassword("password");
			form.setBirthday("1990-01-01");
			
			// Set expectations on the mocked logic
			when(logic.execute(form)).thenReturn("execute");
			// Perform the request and expect certain behavior
			mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"John\", \"password\":\"password\", \"birthday\":\"1990-01-01\"}"))
			.andExpect(status().isOk());
		}
		
		/**
		 * 異常系のテスト
		 */
		@Test
		void test_error() throws Exception {
			
			SignUpForm form = new SignUpForm();
			form.setName("John");
			form.setPassword("password");
			form.setBirthday("1990-01-01");
			
			// Set expectations on the mocked logic
			doThrow(new RuntimeException("Internal Server Error")).when(logic).execute(form);
			// Perform the request and expect certain behavior
			mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"John\", \"password\":\"password\", \"birthday\":\"1990-01-01\"}"))
			.andExpect(status().isInternalServerError());
		}
		
	}

	@Nested
	class Validation {
		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたNameが空だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_name_validateError1() {

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"\", \"password\":\"password\", \"birthday\":\"1990-01-01\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたNameが11文字以上だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_name_validateError2() {

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"aaaaaaaaaaa\", \"password\":\"password\", \"birthday\":\"1990-01-01\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたPasswordが空だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_password_validateError1() {

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"aaa\", \"password\":\"\", \"birthday\":\"1990-01-01\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたPasswordが8文字以外だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_password_validateError2() {

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"\", \"password\":\"password111\", \"birthday\":\"1990-01-01\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたBirthdayが空だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_birthday_validateError1() {

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"aaa\", \"password\":\"\", \"birthday\":\"1990-01-01\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

		/**
		 * 異常系<br/>
		 * ・リクエストパラメータで渡ってきたBirthdayが空だった時<br/>
		 * 
		 * 期待値<br/>
		 * ・badRequestでステータスコード400が返っていること
		 * 
		 */
		@Test
		void test_birthday_validateError2() {

			// 1日後の日付を算出
			LocalDate today = LocalDate.now();
			LocalDate tommorow = today.plusDays(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String tommorowString = tommorow.format(formatter);

			try {
				mockMvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON).content(
						"{\"name\":\"aaa\", \"password\":\"password\", \"birthday\":\"" + tommorowString + "\"}"))
						.andExpect(status().isBadRequest());
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail("失敗");
			}
		}

	}

}
