package com.innomes.main.exception;

public class CProcessNotFoundException extends RuntimeException {
	
	public CProcessNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public CProcessNotFoundException(String msg) {
		super(msg);
	}

	public CProcessNotFoundException() {
		super();
	}
}
