package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;

public class UserManagementAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public UserManagementAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserManagementAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
