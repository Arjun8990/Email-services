package com.technoboost.send_email.service;

import com.technoboost.send_email.payload.SendMailPayload;
import org.springframework.http.ResponseEntity;

public interface MailSendService {
    ResponseEntity<?> sendMail(SendMailPayload payload);
}
