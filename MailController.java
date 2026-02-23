package com.example.DDamo;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    Emailservice emailservice;

    @GetMapping("/mailsend")
    @ResponseBody
    public String Sendmail()
    {

        emailservice.sendSimplemail();
        return "Email send";
    }


    @GetMapping("/sendhtml")
    @ResponseBody
    public String Htmlemail() throws MessagingException
    {
        String htmlContent= "<h1>Welcome!<h1><p>This is second<b>HTML</b> email.</p>";
        emailservice.Sendhtmlemail("vinodathya001@gmail.com","HTML Email Subject",htmlContent);
        return "Send Html mail";
    }

    @GetMapping("/inlineimagemail")
    @ResponseBody
    public String InlineImageEmail() throws MessagingException
    {
        String htmlContent= "<html><body>"+
                "<h3>Here is our logo:</h3>"+
                "<img src='cid:logoImage'>"+
                "</body><html>";
        emailservice.InlineImageEmail("vinodathya001@gmail.com","HTML Email Subject",htmlContent);
        return "Send inline mail";
    }

    @GetMapping("/send-email")
    @ResponseBody
    public String sendEmail()
    {
         try {
             emailservice.Attachmentflag(
                     "vinodathya001@gmail.com",
                     "Subject : Test Email with Attachment",
                     "Please find the attached file.",
                          "C:\\Users\\user\\OneDrive\\Pictures\\Ai Question.jpg"

             );
             return "Email send successfully!";
         }catch (Exception e)
         {
             e.printStackTrace();
             return "Error sending email :" + e.getMessage();
         }





    }
}
