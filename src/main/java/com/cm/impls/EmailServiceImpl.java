package com.cm.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cm.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            message.setFrom(domainName); // have to set sender

            System.out.println("\n\n\nMail send tk to aya haii..\n\n\n");

            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("\n\nException occurs on mail sending part\n\n\n");
            System.out.println(e + "\n\n\n");
        }
    }

    /** We will not implements these.. but these have to learn */
    @Override
    public void sendEmailWithHtml() {

    }

    @Override
    public void sendEmailWithAttachment() {

    }

}
