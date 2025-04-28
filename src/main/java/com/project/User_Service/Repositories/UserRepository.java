package com.project.User_Service.Repositories;

import com.project.User_Service.models.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person,Long> {

}
