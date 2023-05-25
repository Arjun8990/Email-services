package com.technoboost.send_email.service;

import com.technoboost.APIResponse;
import com.technoboost.UtilMethod;
import com.technoboost.send_email.payload.SendMailPayload;
import com.technoboost.send_email.util.Constants;
import com.technoboost.send_email.util.SendMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class MailSendServiceImpl implements MailSendService{
    @Override
    public ResponseEntity<?> sendMail(SendMailPayload payload) {
        String message = SendMailUtil.sendMail(payload.getEmailSubjectLine(), payload.getEmailBodyContent(),
                Constants.MAIL_FROM, payload.getToMail());
        APIResponse res;
        if (message.equalsIgnoreCase("Email Sent")) {
            res = APIResponse.builder()
                    .id(null)
                    .message(message)
                    .path(UtilMethod.getPath())
                    .statusCode(HttpStatus.CREATED.value())
                    .status(HttpStatus.CREATED.name())
                    .success(true)
                    .timestamp(LocalDateTime.now().toString())
                    .build();
        } else {
            res = APIResponse.builder()
                    .id(null)
                    .message(message)
                    .path(UtilMethod.getPath())
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND.name())
                    .success(false)
                    .timestamp(LocalDateTime.now().toString())
                    .build();
        }
        return ResponseEntity.ok(res);
    }
}
