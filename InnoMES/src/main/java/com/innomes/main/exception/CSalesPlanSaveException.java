package com.innomes.main.exception;

public class CSalesPlanSaveException extends RuntimeException {
    public CSalesPlanSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSalesPlanSaveException(String msg) {
        super(msg);
    }

    public CSalesPlanSaveException() {
        super();
    }
}

