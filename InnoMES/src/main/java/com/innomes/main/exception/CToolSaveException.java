package com.innomes.main.exception;

public class CToolSaveException extends RuntimeException {

	 public CToolSaveException(String msg, Throwable t) {
	        super(msg, t);
	    }

	    public CToolSaveException(String msg) {
	        super(msg);
	    }

	    public CToolSaveException() {
	        super();
	    }
}
