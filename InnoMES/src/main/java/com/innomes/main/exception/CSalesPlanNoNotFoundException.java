package com.innomes.main.exception;

public class CSalesPlanNoNotFoundException extends RuntimeException {
    public CSalesPlanNoNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSalesPlanNoNotFoundException(String msg) {
        super(msg);
    }

    public CSalesPlanNoNotFoundException() {
        super();
    }
}

