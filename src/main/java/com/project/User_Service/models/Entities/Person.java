package com.project.User_Service.models.Entities;

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
}
