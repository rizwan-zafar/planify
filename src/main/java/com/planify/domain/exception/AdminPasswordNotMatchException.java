package com.planify.domain.exception;

public class AdminPasswordNotMatchException extends RuntimeException {
    public AdminPasswordNotMatchException(String message) {
        super(String.format("Password and Confirm Password do not match"));
    }
}