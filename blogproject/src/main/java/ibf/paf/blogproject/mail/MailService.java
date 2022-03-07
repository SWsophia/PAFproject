package ibf.paf.blogproject.mail;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {

    public void resetPasswordEmail(String toEmail) throws MessagingException {
        String subject = "Login notification";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("momofeiwen@gmail.com", System.getenv("MAIL_PASSWORD"));
                    }
                });


        Message message = new MimeMessage(session);
        InternetAddress fromAddress = new InternetAddress("momofeiwen@gmail.com");
        InternetAddress[] toAddress = InternetAddress.parse(toEmail, false);

        String body = "Your account is logged in just now!";

        message.setFrom(fromAddress);
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setText(body);
        message.setHeader("X-Mailer", "Tov Are's program");
        message.setSentDate(new Date());

        Transport.send(message);
    }

}
