package com.globallogic.bci.auth.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneDto {
	@JsonProperty("number")
	private long number;
	
	@JsonProperty("citycode")
	private int citycode;
	
	@JsonProperty("countrycode")
	private String countrycode;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
}
