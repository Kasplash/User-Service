package com.project.User_Service.services;

import com.project.User_Service.repositories.UserRepository;
import com.project.User_Service.exeptions.UserAlreadyExistsExeption;
import com.project.User_Service.exeptions.UserNotExistsException;
import com.project.User_Service.models.entities.Person;
import com.project.User_Service.models.requestDTO.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Person save(PersonRequest personRequest) {
        long ssn = personRequest.ssn();
        String name = personRequest.name();
        String address = personRequest.address();
        int age = personRequest.age();
        Person personEntity = new Person(ssn, name, address, age);

        if(userRepository.existsById(ssn)){
            throw new UserAlreadyExistsExeption("User is already present");
        }
        return userRepository.save(personEntity);
    }

    public Optional<Person> get(long ssn) {
        return userRepository.findById(ssn);
    }

    public void delete(long ssn){
        if(!userRepository.existsById(ssn)) {
            throw new UserNotExistsException("No user to delete");
        }
        userRepository.deleteById(ssn);
    }

    public int updateAddress(PersonRequest person){
        int updatedEntries = userRepository.updatePerson(person.ssn(),person.address(), person.name(), person.age());
        if(updatedEntries == 0) {
            throw new UserNotExistsException("No entries to update");
        }
        return updatedEntries;
    }
}
