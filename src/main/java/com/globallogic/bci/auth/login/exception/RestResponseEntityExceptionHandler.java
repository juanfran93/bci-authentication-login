package com.globallogic.bci.auth.login.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.bci.auth.login.api.base.ApiError;
import com.globallogic.bci.auth.login.api.base.StandardRestResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BCIException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		BCIException exception = (BCIException) ex;
		StandardRestResponse<?, ApiError> response = new StandardRestResponse<Object, ApiError>();
		List<ApiError> errors = new ArrayList<ApiError>();
		ApiError error = new ApiError();
		error.setTimestamp(String.valueOf(new Date().getTime()));
		error.setErrorCode(exception.getErrorCode());
		error.setDetails(exception.getDetails());
		errors.add(error);
		response.setErrors(errors);
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}