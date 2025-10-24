package com.project.user_service.exeptions;

public class UserIdIsNull extends RuntimeException {
    public UserIdIsNull(String message) {
        super(message);
    }
}
