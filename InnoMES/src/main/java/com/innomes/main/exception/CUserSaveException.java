package com.innomes.main.exception;

public class CUserSaveException extends RuntimeException {
	public CUserSaveException(String msg, Throwable t) {
		super(msg, t);
	}
	public CUserSaveException(String msg) {
		super(msg);
	}
	public CUserSaveException() {
		super();
	}
}
