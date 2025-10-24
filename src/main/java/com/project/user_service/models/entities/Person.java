package com.project.user_service.models.entities;

import com.project.user_service.exeptions.UserIdIsNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    long ssn;
    String name;
    String address;
    int age;

    public Person() {
    }

    public Person(long ssn, String name, String address, int age) {
        validate(ssn);
        setSsn(ssn);
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void validate(long ssn) {
        if (ssn == 0) {
            throw new UserIdIsNull("ssn cannot be unidentified");
        }
    }
}
