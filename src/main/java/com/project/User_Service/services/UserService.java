package com.project.User_Service.services;

import com.project.User_Service.Repositories.UserRepository;
import com.project.User_Service.models.Entities.Person;
import com.project.User_Service.models.RequestDTO.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Person save(PersonRequest user) {
        Person personEntity = new Person(user.ssn(), user.name(), user.address(), user.age());
        return userRepository.save(personEntity);
    }

    public Optional<Person> get(long ssn) {
        return userRepository.findById(ssn);
    }

    public boolean delete(long ssn){
        if(userRepository.existsById(ssn)) {
            userRepository.deleteById(ssn);
            return true;
        }
        return false;
    }

}
