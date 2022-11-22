package com.globallogic.bci.auth.login.api.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {

	@JsonProperty("timestamp")
	private String timestamp;

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("details")
	private String details;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
