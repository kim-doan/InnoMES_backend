package com.innomes.main.exception;

public class COrderIdNotFoundException extends RuntimeException {
    public COrderIdNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public COrderIdNotFoundException(String msg) {
        super(msg);
    }

    public COrderIdNotFoundException() {
        super();
    }
}

