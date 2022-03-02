package com.fatih.SpringSecurity.servises.email;

import com.fatih.SpringSecurity.controllers.PublicController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService
{
    Logger logger = LoggerFactory.getLogger(PublicController.class);


    public  void SendEmail(){
        logger.info("Email sent");
    }

    public  void  sendMailConfirmation(){
        System.out.println("Email confirmationEmail confirmation mail sent");
        logger.info("Email confirmationEmail confirmation mail sent");
    }

    public  void  mailConfirmed(){
        logger.info("Your email has confirmed successfully.");
    }

    public  void  sendResetPasswordMail(){
        logger.info("Reset password mail sent");
    }



}