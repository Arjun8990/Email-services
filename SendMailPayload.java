package com.technoboost.send_email.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailPayload {
    private String toMail;
    private String emailSubjectLine;
    private String emailBodyContent;
}
