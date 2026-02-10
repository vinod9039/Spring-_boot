package com.example.DDamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@Service
public class OTPController {



    @Autowired
    JavaMailSender mailSender;

    @Autowired
    signupRepositry signupRepositrys;

    @GetMapping("/signup")
    public String SignUp()
    {
        return "ui";
}

    @GetMapping("/googlesignup")
    public String GoogleSignUp( String password,Model model,@AuthenticationPrincipal OAuth2User principal)
    {
        principal.getAttributes().forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        if (principal != null)
        {
            String name =principal.getAttribute("name");
            String email = principal.getAttribute("email");
            Signup signup = new Signup();
            signup.setPassword(password);
            signup.setUsername(name);
            signup.setEmail(email);

            signupRepositrys.save(signup);
            return "homepage";

        }

            return "Notfound";

    }

    @GetMapping("/password")
    public String SavePassword()
    {
        return "userpassword";
    }


    LocalTime time;
    @GetMapping("/sendotp")
    public String SendOTP(String username, String password, String email, Model model)
    {
        Optional<Signup> opt = signupRepositrys.findByEmail(email);

        if(opt.isPresent())
        {
            model.addAttribute("present",true);
            return "ui";
        }
        else {
            Random random = new Random();
            int otp = 1000 + random.nextInt(9000);

            System.out.println("OTP :"+otp);

            LocalTime time = LocalTime.now();
            this.time=time;
            System.out.println(time);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("OTP");
            message.setText("" + otp);
            mailSender.send(message);

            Signup signup = new Signup();
            signup.setUsername(username);
            signup.setPassword(password);
            signup.setEmail(email);
            signup.setOtp(otp);
            signupRepositrys.save(signup);
            model.addAttribute("useremail", email);
            return "confirm-otp";
        }

    }


  @Autowired
  SingupJpqlRepositry repositry;

    @GetMapping("/confirmOTP")
    public String saveUser( int otp, String email, Model model) throws Exception {

        Optional<Signup> opt = signupRepositrys.findByEmail(email);

        System.out.println();

        LocalDateTime userEntryTime = LocalDateTime.now();
        Duration duration = Duration.between(time, userEntryTime);
        System.out.println("Duration : "+ duration);
        long minutesPassed = duration.toMinutes();
        System.out.println("Minutes : "+ minutesPassed);

        if (minutesPassed <= 1)
        {
            if (opt.isPresent()) {

                Signup user = opt.get();

                if (user.getOtp() == otp) {

                    return "homepage";
                } else {
                    model.addAttribute("verify", "Invalid OTP");
                    return "confirm-otp";
                }

            } else {
                model.addAttribute("verify", "Email not found");
                return "confirm-otp";
            }
       }
        else {
            repositry.deleteByEmail(email);
            System.out.println("else chala");
            return "confirm";

            }


    }
}
