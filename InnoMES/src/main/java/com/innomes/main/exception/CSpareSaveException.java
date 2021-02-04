package com.innomes.main.exception;

public class CSpareSaveException extends RuntimeException {
    public CSpareSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSpareSaveException(String msg) {
        super(msg);
    }

    public CSpareSaveException() {
        super();
    }
}

