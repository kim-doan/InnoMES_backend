package com.innomes.main.exception;

public class CValiedationItemCodeException extends RuntimeException {
	public CValiedationItemCodeException(String msg, Throwable t) {
		super(msg, t);
	}
	public CValiedationItemCodeException(String msg) {
		super(msg);
	}
	public CValiedationItemCodeException() {
		super();
	}
}
