package ordermicroservice.email.service;

import freemarker.template.Configuration;
import ordermicroservice.email.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Configuration fmConfiguration;
    @Autowired private JavaMailSender javaMailSender;

    @Override
    public String getContentFromTemplate(String template, Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public Mail getConfirmationMail(String name,String toEmail,String ftlName,Long orderId){
        Map<String,Object>mailFormat= new HashMap<>();
        mailFormat.put("firstName",name);
        mailFormat.put("orderSuccess","Your package will be delivered between 7:00AM and 9:00PM by our Amazon Delivery Agent (Phone: +911161264444 PIN 5148). " +
                "To ensure your safety, the Delivery Agent will drop the package at your doorstep, ring the doorbell and then move back 2 meters while waiting for you to collect your package. If you are in a containment zone, the agent will call you and request you to collect your package from the nearest accessible point while following the same No-Contact delivery process.");
        String content=getContentFromTemplate(ftlName,mailFormat);
        Mail mail = new Mail();
        mail.setToEmail(toEmail);
        mail.setContent(content);
        mail.setSubject(name+", your order saved successfully thank you for order with us, your oder id is "+orderId);
        return mail;
    }

    @Override
    public Mail getMail(String name,String toEmail,String orderId){
        Map<String,Object> mailFormat= new HashMap<>();
        mailFormat.put("firstName",name);
        mailFormat.put("orderId",orderId);
        mailFormat.put("orderSuccess","Your order has been saved successfully.You will be notify once your order will be ship." +
                "To ensure your safety, the Delivery Agent will drop the package at your doorstep, ring the doorbell and then move back 2 meters while waiting for you to collect your package. If you are in a containment zone, the agent will call you and request you to collect your package from the nearest accessible point while following the same No-Contact delivery process.");
        String content=getContentFromTemplate("confirmation-email.ftl",mailFormat);
        Mail mail = new Mail();
        mail.setToEmail(toEmail);
        mail.setContent(content);
        mail.setSubject("Order saved successfully. Your order Id is "+orderId);
        return mail;
    }

    public boolean sendMail(Mail mail) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(mail.getSubject());
            helper.setTo(mail.getToEmail());
            String emailContent = mail.getContent();
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
            return true;
        }catch (Exception exception){
            System.out.println("Exception occured during mail send "+exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }
}
