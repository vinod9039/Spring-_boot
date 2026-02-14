package com.example.DDamo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonsJPQLRepositry extends JpaRepository<Persons,Integer> {
    @Query("select p from Persons p")
    List<Persons> getAllPersons();

    @Query("select p from Persons p where p.state=:state")
    List<Persons> findByState(@Param("state") String st);

    @Modifying
    @Transactional
    @Query("update Persons p set p.state=:state where p.id=:id")
    int updatestate(String state,@Param("id") int pid);

    @Modifying
    @Transactional
    @Query("delete from Persons p where p.id =:id")
    int deleteById(@Param("id") int id);




}
