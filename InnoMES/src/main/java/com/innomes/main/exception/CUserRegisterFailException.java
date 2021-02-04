package com.innomes.main.exception;

public class CUserRegisterFailException extends RuntimeException {
	public CUserRegisterFailException(String msg, Throwable t) {
		super(msg, t);
	}
	public CUserRegisterFailException(String msg) {
		super(msg);
	}
	public CUserRegisterFailException() {
		super();
	}
}
