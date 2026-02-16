package com.example.securitydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepositry userRepositry;

    @Override
    public void run(String...args)
    {
        Users users= new Users();
        users.setUsername("vinod");
        users.setPassword(passwordEncoder.encode("Vinod@82"));
        users.setEnabled(true);

        Roles roles = new Roles();
        roles.setName("USER");

        users.setRoles(Set.of(roles));
         userRepositry.save(users);

    }
}
