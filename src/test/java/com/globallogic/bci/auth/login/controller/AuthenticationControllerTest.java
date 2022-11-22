package com.globallogic.bci.auth.login.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.bci.auth.login.api.base.ApiError;
import com.globallogic.bci.auth.login.api.base.StandardRestResponse;
import com.globallogic.bci.auth.login.dto.SignUpRequest;
import com.globallogic.bci.auth.login.dto.SignUpResponse;
import com.globallogic.bci.auth.login.service.impl.AuthenticationServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AuthenticationServiceImpl service;

	@Autowired
	private ObjectMapper objectMapper;

	private SignUpRequest user;

	@BeforeEach
	public void setUp() {
		user = new SignUpRequest();
		user.setEmail("est@Test.com");
		user.setPassword("Password00");

		SignUpResponse response = new SignUpResponse();
		response.setToken("token");
		response.setActive(true);
		response.setId(UUID.randomUUID());

		when(service.signUp(any())).thenReturn(new SignUpResponse());
	}

	@Test
	public void signUpOk() throws Exception {
		MockHttpServletResponse httpResponse = mvc
				.perform(
						post("/v1/bci/authentication-login/sign-up").contentType("application/json").content(objectMapper.writeValueAsString(user)))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		StandardRestResponse<SignUpResponse, ApiError> actualResult = objectMapper
				.readValue(httpResponse.getContentAsString(), StandardRestResponse.class);
		assertNotNull(actualResult);
		assertNotNull(actualResult.getData());
		List<SignUpResponse> data = actualResult.getData();
		assertEquals(data.size(), 1);
	}

}
