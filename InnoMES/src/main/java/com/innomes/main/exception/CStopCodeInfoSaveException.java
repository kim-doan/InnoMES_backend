package com.innomes.main.exception;

public class CStopCodeInfoSaveException extends RuntimeException {
    public CStopCodeInfoSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CStopCodeInfoSaveException(String msg) {
        super(msg);
    }

    public CStopCodeInfoSaveException() {
        super();
    }
}
