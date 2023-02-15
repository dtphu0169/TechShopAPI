package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Account;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class SendmailServiceImpl implements SendmailService{
    final String username = "19130169@st.hcmuaf.edu.vn";
    final String password = "yfexrxewmctecrnl";

    @Override
    public boolean sendmail(String toEmail, String subject, String msg) {
        boolean result = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TechShop"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean sendVerificationEmail(Account account, String verify) {
        boolean result = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String msg  = "Dear "+ account.getUserName() +",\n"
                + "Please click the link below to verify your registration:\n"
                + verify + "\n"
                + "Thank you,\n"
                + "TechShop.";

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TechShop"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account.getEmail()));
            message.setSubject("Verify account");
            message.setText(msg);
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean sendmailResetpassword(Account account, String verify) {

        String msg  = "Dear "+ account.getUserName() +",\n"
                + "Please click the link below to reset your password:\n"
                + verify + "\n"
                + "Thank you,\n"
                + "TechShop.";


        return sendmail(account.getEmail(),"Reset password TechShop account",msg);
    }


}
