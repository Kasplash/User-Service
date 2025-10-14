package com.project.User_Service.models.requestDTO;


public record PersonRequest(long ssn, String name, String address, int age) {
}
