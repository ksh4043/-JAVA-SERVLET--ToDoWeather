package service;

import jakarta.mail.Session;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import jakarta.activation.*;
import jakarta.activation.spi.*;
import jakarta.mail.*;
import java.util.Properties;

public class EmailSender {
    public static boolean sendVerificationEmail(String toEmail, String authcode) {
        String[] parts = toEmail.split("@");
        if (parts.length != 2) return false;

        String domain = parts[1];
        Properties domainProp = new Properties();
        EmailProperties emailProp = new EmailProperties();
        System.out.println("Create Instance for Properties");
        System.out.println("domain : " + domain);
        domainProp = emailProp.readProperties(domain);
        
        if (domainProp == null) {
            System.out.println("Properties 파일을 찾을 수 없음");
            return false;
        }

        String fromEmail = domainProp.getProperty("mail.email");
        String password = domainProp.getProperty("mail.password");

        // SMTP 설정용 Properties 구성
        Properties smtpProps = new Properties();
        smtpProps.put("mail.smtp.auth", "true");

        // .properties에서 가져온 설정 주입
        smtpProps.put("mail.smtp.host", domainProp.getProperty("mail.smtp.host"));
        smtpProps.put("mail.smtp.port", domainProp.getProperty("mail.smtp.port"));
        System.out.println("port & host :" + smtpProps.getProperty("mail.smtp.host") + " " + smtpProps.getProperty("mail.smtp.port"));

        if ("465".equals(smtpProps.getProperty("mail.smtp.port"))) {
            smtpProps.put("mail.smtp.ssl.enable", "true");
        } else if ("587".equals(smtpProps.getProperty("mail.smtp.port"))) {
            smtpProps.put("mail.smtp.starttls.enable", "true");
        }

        try {
            sendEmailWithSession(smtpProps, fromEmail, password, toEmail, authcode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void sendEmailWithSession(Properties props, String fromEmail, String password, String toEmail, String code) throws MessagingException {

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("이메일 인증 코드입니다.");
        message.setText("인증 코드: " + code);

        Transport.send(message);
        System.out.println("메일 전송 완료: " + toEmail);
    }
}
