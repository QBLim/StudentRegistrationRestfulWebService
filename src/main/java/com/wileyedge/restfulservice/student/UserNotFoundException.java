package com.wileyedge.restfulservice.student;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404 Response
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String msg) {
		super(msg);
		
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
