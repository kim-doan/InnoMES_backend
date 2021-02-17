package com.innomes.main.exception;

public class CLoginFailException extends RuntimeException {
	public CLoginFailException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public CLoginFailException(String msg) {
		super(msg);
	}
	public CLoginFailException() {
		super();
	}
}

