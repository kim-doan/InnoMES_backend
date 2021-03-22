package com.innomes.main.exception;

public class CStopCodeDuplicateException extends RuntimeException {
    public CStopCodeDuplicateException(String msg, Throwable t) {
        super(msg, t);
    }

    public CStopCodeDuplicateException(String msg) {
        super(msg);
    }

    public CStopCodeDuplicateException() {
        super();
    }
}
