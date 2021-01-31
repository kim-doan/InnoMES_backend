package com.innomes.main.exception;

public class CToolNotFoundException extends RuntimeException {

	public CToolNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public CToolNotFoundException(String msg) {
		super(msg);
	}

	public CToolNotFoundException() {
		super();
	}
}
