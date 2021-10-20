//package com.kingfood.backend.EmailUtils;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//
//@Service
//public class EmailServiceUtil {
//    private JavaMailSender javaMailSender;
//
//    public EmailServiceUtil(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//
//    public void sendMail(String toEmail, String subject, String message) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//        String htmlMsg = message;
//        helper.setText(htmlMsg, true);
//        helper.setTo(toEmail);
//        helper.setSubject(subject);
//        javaMailSender.send(mimeMessage);
//    }
//}
