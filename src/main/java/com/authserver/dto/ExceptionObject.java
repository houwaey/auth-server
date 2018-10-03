package com.authserver.dto;

import org.springframework.http.HttpStatus;

public class ExceptionObject {

	private int code;
	private String message;
	private String developerMessage;
	private String uri;
	
	public ExceptionObject(HttpStatus httpStatus, String message, String developerMessage, String uri) {
		super();
		this.code = httpStatus.value();
		this.message = message == null ? "System is busy. Please contact your Administrator." : message;
		this.developerMessage = developerMessage;
		this.uri = uri;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "ErrorObject [code=" + code + ", message=" + message + ", developerMessage=" + developerMessage
				+ ", uri=" + uri + "]";
	}
	
}
