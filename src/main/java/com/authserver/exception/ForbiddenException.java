package com.authserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException() {
		super("Forbidden exception");
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(String message, Throwable e) {
		super(message, e);
	}
}
