package com.example.DDamo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SMSController {
    @GetMapping("/sendsms")
    @ResponseBody
    public ResponseEntity<String> Sendsms(){
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"),System.getenv("TWILIO_AUTH_TOKEN"));
        Message.creator(new PhoneNumber("+919039502082"),
        new PhoneNumber("+19893945094"), "Hello Vinod").create();
        return new ResponseEntity<>("SUccess" ,HttpStatus.OK);
    }


    @GetMapping("/sms")
    public String SMSui()
    {
        return "smsui";
    }

    @PostMapping("/sendsms")
    @ResponseBody
    public String sendsms(@RequestParam("mobileno") String mobileno,@RequestParam("message") String message)
    {
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"),System.getenv("TWILIO_AUTH_TOKEN"));
        Message.creator(new PhoneNumber("+91"+mobileno),
                new PhoneNumber("+19893945094"),message).create();
        return " sms send successfull";
    }

}

