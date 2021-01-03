package com.innomes.main.exception;

public class CProductSaveException extends RuntimeException {
    public CProductSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CProductSaveException(String msg) {
        super(msg);
    }

    public CProductSaveException() {
        super();
    }
}

