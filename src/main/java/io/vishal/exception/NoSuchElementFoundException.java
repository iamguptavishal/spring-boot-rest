package io.vishal.exception;

public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException() {
    }
    public NoSuchElementFoundException(String message) {
        super(message);
    }
}
