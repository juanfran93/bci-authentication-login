package com.globallogic.bci.auth.login.service;

import com.globallogic.bci.auth.login.dto.SignUpRequest;
import com.globallogic.bci.auth.login.dto.SignUpResponse;

public interface AuthenticationService {
	
	public SignUpResponse signUp(SignUpRequest request);

}
