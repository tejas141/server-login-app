package com.example.demo.login.exception;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public LoginException() {
		this.message = "Login Exception";
	}

	public LoginException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "LoginException [message=" + message + "]";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
