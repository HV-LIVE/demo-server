package org.hvlive.server.demoserver.exception;

public class ErrorException extends RuntimeException {
    private int code;

    public ErrorException(int code) {
        this.code = code;
    }

    public ErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
