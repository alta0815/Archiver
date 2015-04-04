package com.un1acker.archiver.exception;

/**
 * un1acker
 * 04.04.2015
 */
public class ZipException extends Exception {
    private static final long serialVersionUID = 4042015L;

    public ZipException() {

    }

    public ZipException(String msg) {
        super(msg);
    }

    public ZipException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
