package com.project.User_Service.exeptions;

public class UserIdIsNull extends RuntimeException {
    public UserIdIsNull(String message) {
        super(message);
    }
}
