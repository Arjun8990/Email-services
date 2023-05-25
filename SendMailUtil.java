
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Slf4j
public class SendMailUtil {
    private static Properties props = null;
    private static Session session = null;

    private static Session getSession() {
        if (session == null) {
            session = Session.getInstance(getProps(), new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Constants.MAIL_SMTP_USERNAME, Constants.MAIL_SMTP_PASSWORD);
                }
            });
        }
        return session;
    }

    public static String sendMail(String emailSubjectLine, String emailBodyContent,
                                  String emailFrom, String emailTo) {
        log.info("Sending Email to " + emailTo);
        log.info("Email Body " + emailBodyContent);
        String res;
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("sushil@truevisual.io"));
            message.setSubject(emailSubjectLine);
            message.setContent(emailBodyContent, "text/html");
            Transport.send(message);
            log.info("Email Sent Successfully");
            res = "Email Sent";
        } catch (MessagingException e) {
            log.info("Email Sending Failure: {}", e.getMessage());
            res = "Email Sending Failure: " + e.getMessage();
        }
        return res;
    }

    public static Properties getProps() {
        if (props == null) {
            props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "email-smtp.ap-south-1.amazonaws.com");
        }
        return props;
    }
}
