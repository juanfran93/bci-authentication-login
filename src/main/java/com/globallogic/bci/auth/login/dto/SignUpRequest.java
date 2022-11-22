package com.globallogic.bci.auth.login.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpRequest {

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	@NotNull(message = "email no puede ser nulo")
	private String email;

	@JsonProperty("password")
	@NotNull(message = "password no puede ser nulo")
	private String password;

	@JsonProperty("phones")
	private List<PhoneDto> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}

	
}
