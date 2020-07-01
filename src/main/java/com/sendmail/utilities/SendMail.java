package com.sendmail.utilities;

import com.sendmail.base.Base;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail extends Base {

    public static void sendMail() {

        //Recipient's Email ID
        String to=config.getProperty("emailToList");

        //Sender's Email ID
        String from=config.getProperty("emailFrom");

        //SMTP Hostname
        String host=config.getProperty("emailHostName");

        //Get System Properties
        Properties properties=System.getProperties();

        //Setup Mail Server
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",465);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth", "true");

        //Get Session Object and Pass
        Session session=Session.getInstance(properties, new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getProperty("authUsername"),config.getProperty("authPassword"));
            }
        });

        try{
            //Create default MimeMessage Object
            MimeMessage message=new MimeMessage(session);

            //Set From: header field
            message.setFrom(new InternetAddress(from));

            //Set To: Splitting emailToList on delimiter ";" and adding them to recipient list
            String[] toList =to.split(";");
            for (String s : toList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            }
            //Set Subject: header field
            message.setSubject("**Important Report**");

            //Set Text: Email body
            message.setText("Hello World! This is an Important Report");

            //Send Message
            System.out.println("Sending Email..");
            Transport.send(message);
            System.out.println("Email Sent Successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email");
            System.out.println(e.getMessage());
        }
    }
}
