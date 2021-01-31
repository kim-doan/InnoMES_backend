package com.innomes.main.exception;

public class CSalesOrderSaveException extends RuntimeException {
    public CSalesOrderSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSalesOrderSaveException(String msg) {
        super(msg);
    }

    public CSalesOrderSaveException() {
        super();
    }
}

