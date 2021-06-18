package com.innomes.main.exception;

public class CManufactureProcessNotFoundException extends RuntimeException {
	public CManufactureProcessNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public CManufactureProcessNotFoundException(String msg) {
		super(msg);
	}
	public CManufactureProcessNotFoundException() {
		super();
	}
}

