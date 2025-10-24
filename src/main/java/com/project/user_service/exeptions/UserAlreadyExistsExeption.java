package com.project.user_service.exeptions;

public class UserAlreadyExistsExeption extends RuntimeException{
    public UserAlreadyExistsExeption(String msg){
        super(msg);
    }
}
