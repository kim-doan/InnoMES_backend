package com.innomes.main.exception;

public class CBadCodeDuplicateException extends RuntimeException {
    public CBadCodeDuplicateException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBadCodeDuplicateException(String msg) {
        super(msg);
    }

    public CBadCodeDuplicateException() {
        super();
    }
}
