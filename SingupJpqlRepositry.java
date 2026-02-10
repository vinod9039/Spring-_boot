package com.example.DDamo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SingupJpqlRepositry extends JpaRepository<Signup,Integer> {

    @Modifying
    @Transactional
    @Query("delete from Signup p where p.email =:email")
    int  deleteByEmail(@Param("email") String email);
}
