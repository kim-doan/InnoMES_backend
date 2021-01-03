package com.innomes.main.exception;

public class CSessionNotFoundException extends RuntimeException{
    public CSessionNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    public CSessionNotFoundException(String msg) {
        super(msg);
    }
    public CSessionNotFoundException() {
        super();
    }
}
