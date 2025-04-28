package com.project.User_Service.controllers;

import com.project.User_Service.models.Entities.Person;
import com.project.User_Service.models.RequestDTO.PersonRequest;

import com.project.User_Service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("User")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Person createPerson(@RequestBody PersonRequest user) {
        return userService.save(user);
    }

    @GetMapping
    public Optional<Person> getPerson(@RequestParam long ssn) {
        return userService.get(ssn);
    }
}
