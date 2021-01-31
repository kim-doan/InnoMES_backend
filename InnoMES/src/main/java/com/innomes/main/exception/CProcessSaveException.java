package com.innomes.main.exception;

public class CProcessSaveException extends RuntimeException{
	public CProcessSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CProcessSaveException(String msg) {
        super(msg);
    }

    public CProcessSaveException() {
        super();
    }
}
