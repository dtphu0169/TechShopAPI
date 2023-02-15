package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface SendmailService {
    public boolean sendmail(String toEmail, String subject, String msg);

    public boolean sendVerificationEmail(Account account,String verify);

    boolean sendmailResetpassword(Account account, String s);
}
