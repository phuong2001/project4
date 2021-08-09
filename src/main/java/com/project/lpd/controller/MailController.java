package com.project.lpd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MailController {

    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping("/help")
    public String showForm(){

        return "help";
    }
@PostMapping("/sendmail")
public  String sendMail(HttpServletRequest request){

    String subject = request.getParameter("subject");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String message = request.getParameter("message");

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


    simpleMailMessage.setTo("dudqth1908047@fpt.edu.vn");

     String mailSubject = name + "has sent a message";
     String mailContent = "Subject: " + subject + "\n";
     mailContent += "Name: " + name + "\n";
     mailContent += "Email: " + email + "\n";
     mailContent += "Subject: " + subject + "\n";
     mailContent += "Phone: " + phone + "\n";
     mailContent += "Message: " + message + "\n";

    simpleMailMessage.setSubject(mailSubject);
    simpleMailMessage.setText(mailContent);

    javaMailSender.send(simpleMailMessage);

    return "send";
}

}

