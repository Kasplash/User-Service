package com.project.User_Service.controllers;

import com.project.User_Service.exeptions.UserAlreadyExistsExeption;
import com.project.User_Service.exeptions.UserNotExistsException;
import com.project.User_Service.models.Entities.Person;
import com.project.User_Service.models.RequestDTO.PersonRequest;

import com.project.User_Service.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("User")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Person createPerson(@RequestBody PersonRequest person) {
        return userService.save(person);
    }

    @GetMapping
    public Optional<Person> getPerson(@RequestParam long ssn) {
        return userService.get(ssn);
    }

    @DeleteMapping
    public void deletePerson(@RequestParam long ssn){
        userService.delete(ssn); // is a void action so no data to return
    }

    @PatchMapping
    public ResponseEntity<String> patchAddress(@RequestBody PersonRequest person){
        return new ResponseEntity<>("Updated entries: " + userService.updateAddress(person), HttpStatus.CREATED);
    }

    @ExceptionHandler(UserAlreadyExistsExeption.class)
    public ResponseEntity<?> handleUserAlreadyExisting(UserAlreadyExistsExeption ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<?> handleNoUserException(UserNotExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
