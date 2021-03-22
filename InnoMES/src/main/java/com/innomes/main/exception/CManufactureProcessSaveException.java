package com.innomes.main.exception;

public class CManufactureProcessSaveException extends RuntimeException {
	public CManufactureProcessSaveException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public CManufactureProcessSaveException(String msg) {
		super(msg);
	}
	public CManufactureProcessSaveException() {
		super();
	}
}

