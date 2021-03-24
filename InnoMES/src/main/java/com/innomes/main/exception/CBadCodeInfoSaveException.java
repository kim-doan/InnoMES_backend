package com.innomes.main.exception;

public class CBadCodeInfoSaveException extends RuntimeException {
    public CBadCodeInfoSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBadCodeInfoSaveException(String msg) {
        super(msg);
    }

    public CBadCodeInfoSaveException() {
        super();
    }
}
