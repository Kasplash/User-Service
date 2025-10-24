package com.project.user_service.exeptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException(String msg){
        super(msg);
    }
}
