package com.innomes.main.exception;

public class CPriceSaveException extends RuntimeException{
	public CPriceSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CPriceSaveException(String msg) {
        super(msg);
    }

    public CPriceSaveException() {
        super();
    }
}
