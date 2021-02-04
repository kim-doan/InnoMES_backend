package com.innomes.main.exception;

public class CMaterialSaveException extends RuntimeException {
    public CMaterialSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CMaterialSaveException(String msg) {
        super(msg);
    }

    public CMaterialSaveException() {
        super();
    }
}

