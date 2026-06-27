package com.example.trainee.bank.app.dto;

import java.util.List;

import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@NoArgsConstructor
public class ServiceResponse {
	private String code;
	private String message;
	private List<JSONObject> details;

	public ServiceResponse(String code, String message, List<JSONObject> details) {
		super();
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<JSONObject> getDetails() {
		return details;
	}

	public void setDetails(List<JSONObject> details) {
		this.details = details;
	}
}
