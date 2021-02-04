package com.innomes.main.exception;

public class CUserDuplicationException extends RuntimeException {
	public CUserDuplicationException(String msg, Throwable t) {
		super(msg, t);
	}
	public CUserDuplicationException(String msg) {
		super(msg);
	}
	public CUserDuplicationException() {
		super();
	}
}
