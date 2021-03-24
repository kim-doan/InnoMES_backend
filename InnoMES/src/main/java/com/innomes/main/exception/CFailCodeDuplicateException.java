package com.innomes.main.exception;

public class CFailCodeDuplicateException extends RuntimeException {
    public CFailCodeDuplicateException(String msg, Throwable t) {
        super(msg, t);
    }

    public CFailCodeDuplicateException(String msg) {
        super(msg);
    }

    public CFailCodeDuplicateException() {
        super();
    }
}
