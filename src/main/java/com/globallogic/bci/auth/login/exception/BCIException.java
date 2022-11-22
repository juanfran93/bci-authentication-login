package com.globallogic.bci.auth.login.exception;

public class BCIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String details;

	public BCIException(String errorCode, String details) {
		super();
		this.errorCode = errorCode;
		this.details = details;
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
