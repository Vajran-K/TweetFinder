package com.example;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender{
    
    public static void sendEmail(String subject, String body){
        String from = ConfigLoader.get("mail.from");
        String password = ConfigLoader.get("mail.password");
        String to = ConfigLoader.get("mail.to");

        Properties props = new Properties();
        props.put("mail.smtp.host", ConfigLoader.get("mail.smtp.host"));
        props.put("mail.smtp.port", ConfigLoader.get("mail.smtp.port"));
        props.put("mail.smtp.auth", ConfigLoader.get("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", ConfigLoader.get("mail.smtp.starttls.enable"));
 
        Session session = Session.getInstance(props,
            new Authenticator(){

                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(from, password);
                }
            }
        );

        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
        } catch(MessagingException e){
            e.printStackTrace();
        }
    }
}