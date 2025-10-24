package com.project.user_service.services;

import com.project.user_service.repositories.UserRepository;
import com.project.user_service.exeptions.UserAlreadyExistsExeption;
import com.project.user_service.exeptions.UserNotExistsException;
import com.project.user_service.models.entities.Person;
import com.project.user_service.models.requests.PersonRequest;
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
            throw new UserAlreadyExistsExeption("User already exists");
        }
        return userRepository.save(personEntity);
    }

    public Optional<Person> get(long ssn) {
        Optional<Person> result = userRepository.findById(ssn);
        if(result.isEmpty()){
            throw new UserNotExistsException("No user found");
        }
        return result;
    }

    public void delete(long ssn){
        if(!userRepository.existsById(ssn)) {
            throw new UserNotExistsException("No user to delete");
        }
        userRepository.deleteById(ssn);
    }

    public int updatePerson(PersonRequest person){
        long ssn = person.ssn();
        String address = person.address();
        String name = person.name();
        int age = person.age();

        int updatedEntries = userRepository.updatePerson(ssn,address, name, age);
        if(updatedEntries == 0) {
            throw new UserNotExistsException("No entries to update");
        }
        return updatedEntries;
    }
}
