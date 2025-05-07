package com.project.User_Service.exeptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException(String msg){
        super(msg);
    }
}
