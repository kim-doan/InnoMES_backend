package com.innomes.main.exception;

public class CFailCodeInfoSaveException extends RuntimeException {
    public CFailCodeInfoSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CFailCodeInfoSaveException(String msg) {
        super(msg);
    }

    public CFailCodeInfoSaveException() {
        super();
    }
}
