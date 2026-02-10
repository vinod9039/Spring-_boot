package com.example.securitydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String Home()
    {
        return "home";
    }

    @GetMapping("/login")
    public String Login()
    {
        return "login";
    }


}
