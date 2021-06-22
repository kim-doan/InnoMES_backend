package com.innomes.main.exception;

public class CParametersShortException extends RuntimeException {
    public CParametersShortException(String msg, Throwable t) {
        super(msg, t);
    }

    public CParametersShortException(String msg) {
        super(msg);
    }

    public CParametersShortException() {
        super();
    }
}
