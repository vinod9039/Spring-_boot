package com.example.DDamo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface signupRepositry extends JpaRepository<Signup,Integer> {

    Optional<Signup> findByEmail(String email);
    Optional<Signup> deleteByEmail(String email);

}
