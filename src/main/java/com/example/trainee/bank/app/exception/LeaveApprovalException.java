package com.example.trainee.bank.app.exception;

public class LeaveApprovalException extends RuntimeException{
    public LeaveApprovalException() {
    }

    public LeaveApprovalException(String message) {
        super(message);
    }

    public LeaveApprovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeaveApprovalException(Throwable cause) {
        super(cause);
    }

    public LeaveApprovalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
