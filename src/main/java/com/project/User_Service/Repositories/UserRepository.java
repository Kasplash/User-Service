package com.project.User_Service.Repositories;

import com.project.User_Service.models.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Person,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Person p SET p.address = :address WHERE p.ssn = :ssn")
    int updateAddress(@Param("address") String address, @Param("ssn") long ssn);
}
