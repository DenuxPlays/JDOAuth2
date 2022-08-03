package dev.denux.jdaoauth2.exceptions;

public class HttpFailedResponseException extends RuntimeException {

    public HttpFailedResponseException(String message, int code) {
        super(String.format("%s (code: %d)", message, code));
    }
}
