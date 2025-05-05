package com.fridge.community_fridge_backend.service;

import com.fridge.community_fridge_backend.config.TwilioConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;
    private final TwilioConfig twilioConfig;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            log.info("Email sent to {}", to);
        } catch (Exception e) {
            log.error("Email failed to {}: {}", to, e.getMessage());
        }
    }

    public void sendSms(String to, String body) {
        try {
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioConfig.getFromNumber()),
                    body
            ).create();
            log.info("SMS sent to {}", to);
        } catch (Exception e) {
            log.error("SMS failed to {}: {}", to, e.getMessage());
        }
    }
}
