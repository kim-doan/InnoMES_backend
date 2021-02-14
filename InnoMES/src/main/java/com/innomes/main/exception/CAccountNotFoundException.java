package com.innomes.main.exception;

public class CAccountNotFoundException extends RuntimeException {
    public CAccountNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAccountNotFoundException(String msg) {
        super(msg);
    }

    public CAccountNotFoundException() {
        super();
    }
}

