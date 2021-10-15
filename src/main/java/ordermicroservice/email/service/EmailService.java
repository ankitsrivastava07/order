package ordermicroservice.email.service;

import ordermicroservice.email.Mail;

import java.util.Map;

public interface EmailService {

    String getContentFromTemplate(String template, Map<String, Object> model);

    Mail getConfirmationMail(String name, String toEmail, String ftlName, Long orderId);

    Mail getMail(String name,String toEmail,String orderId);

    boolean sendMail(Mail mail);

 }
