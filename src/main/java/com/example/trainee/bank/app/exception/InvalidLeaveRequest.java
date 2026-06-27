package com.example.trainee.bank.app.exception;

public class InvalidLeaveRequest extends RuntimeException{
    public InvalidLeaveRequest() {
    }

    public InvalidLeaveRequest(String message) {
        super(message);
    }

    public InvalidLeaveRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLeaveRequest(Throwable cause) {
        super(cause);
    }

    public InvalidLeaveRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
