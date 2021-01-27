package com.innomes.main.exception;

public class CCompanySaveException extends RuntimeException {
	public CCompanySaveException(String msg, Throwable t) {
		super(msg, t);
	}
	public CCompanySaveException(String msg) {
		super(msg);
	}
	public CCompanySaveException() {
		super();
	}
}
