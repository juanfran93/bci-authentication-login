package com.globallogic.bci.auth.login.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.bci.auth.login.api.base.ApiError;
import com.globallogic.bci.auth.login.api.base.StandardRestResponse;
import com.globallogic.bci.auth.login.dto.SignUpRequest;
import com.globallogic.bci.auth.login.dto.SignUpResponse;
import com.globallogic.bci.auth.login.service.AuthenticationService;

@RestController
@RequestMapping("/v1/bci/authentication-login")
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService service;

	@PostMapping(path = "/sign-up", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<StandardRestResponse> signUp(@RequestBody @Valid SignUpRequest request) throws Exception {

		LOGGER.info("Starting sign up process...");
		SignUpResponse response = service.signUp(request);
		return new ResponseEntity<StandardRestResponse>(
				new StandardRestResponse<SignUpResponse, ApiError>(Arrays.asList(response)), HttpStatus.CREATED);
	}
	
}
