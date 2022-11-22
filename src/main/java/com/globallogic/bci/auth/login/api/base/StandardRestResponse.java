package com.globallogic.bci.auth.login.api.base;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardRestResponse<T, S> {

	@JsonProperty("data")
	private List<T> data;
	@JsonProperty("errors")
	private List<S> errors;

	public StandardRestResponse() {
		super();
		this.data = new ArrayList<T>();
		this.errors = new ArrayList<S>();
	}

	public StandardRestResponse(List<T> data) {
		super();
		this.data = data;
		this.errors = new ArrayList<S>();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<S> getErrors() {
		return errors;
	}

	public void setErrors(List<S> errors) {
		this.errors = errors;
	}

}
