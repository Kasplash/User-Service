package com.project.User_Service.models.RequestDTO;


public record PersonRequest(long ssn, String name, String address, int age) {
}
