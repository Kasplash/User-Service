package com.project.User_Service.services;

import com.project.User_Service.Repositories.UserRepository;
import com.project.User_Service.exeptions.UserAlreadyExistsExeption;
import com.project.User_Service.exeptions.UserNotExistsException;
import com.project.User_Service.models.Entities.Person;
import com.project.User_Service.models.RequestDTO.PersonRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Person save(PersonRequest person) {
        long ssn = person.ssn();
        Person personEntity = new Person(ssn, person.name(), person.address(), person.age());
        if(userRepository.existsById(person.ssn())){
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
        return userRepository.updateAddress(person.address(), person.ssn());
    }

}
