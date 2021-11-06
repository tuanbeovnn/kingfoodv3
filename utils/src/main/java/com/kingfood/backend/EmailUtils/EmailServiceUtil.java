package com.kingfood.backend.EmailUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;


@Service
public class EmailServiceUtil {
    private final JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    public EmailServiceUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = message;
        helper.setText(htmlMsg, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

    private void sendEmail(String toEmail, String subject, String message, boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(toEmail);
            String htmlMsg = message;
            helper.setSubject(subject);
            helper.setText(htmlMsg, isHtml);
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendEmailActiveCode(String email, long expire, String signature) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("email", email);
        velocityContext.put("expire", expire);
        velocityContext.put("signature", signature);
        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate("templates/sendreset.html", "UTF-8");
        template.merge(velocityContext, writer);
        this.sendEmail(email, "Mã kích hoạt tài khoản PlusPlus", writer.toString(), true);
    }


}
