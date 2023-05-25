package com.technoboost.send_email.resource;

import com.technoboost.send_email.payload.SendMailPayload;
import com.technoboost.send_email.service.MailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail-send")
public class SendMailResource {
    private final MailSendService mailSendService;
    @PostMapping
    public ResponseEntity<?> sendMail(@Valid @RequestBody SendMailPayload payload){
        return mailSendService.sendMail(payload);
    }
}
