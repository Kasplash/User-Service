package com.project.User_Service.exeptions;

public class UserAlreadyExistsExeption extends RuntimeException{
    public UserAlreadyExistsExeption(String msg){
        super(msg);
    }
}
