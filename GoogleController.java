package com.example.DDamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoogleController {

    @GetMapping("/user")
    @ResponseBody
    public String user(@AuthenticationPrincipal OAuth2User principal)
    {
        String name=principal.getAttribute("name");
        String email = principal.getAttribute("email");
        return "Name : " +name+ ", Email : " + email;
    }

    @GetMapping("/profile")
    public String Profile(Model model,@AuthenticationPrincipal OAuth2User principal)
    {
        if (principal !=null)
        {
            model.addAttribute("name",principal.getAttribute("name"));
            model.addAttribute("email",principal.getAttribute("email"));
            model.addAttribute("picture",principal.getAttribute("picture"));

        }
        return "profile";

    }

    @GetMapping("/logins")
    public String GoogleSignIn()
    {
        return "login";
    }

    @Autowired
    loginRepositry loginRepositry;

    @GetMapping("/googlesignin")
    public String SingIn(Model model,@AuthenticationPrincipal OAuth2User principal)
    {
        if (principal != null)
        {
            String username= principal.getAttribute("name");
            String email=principal.getAttribute("email");

            GoogleSignIn googleSignIn =new GoogleSignIn();

            googleSignIn.setUsername(username);
            googleSignIn.setEmail(email);
            loginRepositry.save(googleSignIn);

            return "homepage";
        }
        return "Notfound";
    }

    @PostMapping("/locallogin")
    @ResponseBody
    public String locallogin()
    {
        return "wellcome";
    }
}
