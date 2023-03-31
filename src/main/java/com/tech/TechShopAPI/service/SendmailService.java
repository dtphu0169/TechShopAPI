package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.Bill;
import com.tech.TechShopAPI.model.Bill_detail;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface SendmailService {
    public boolean sendmail(String toEmail, String subject, String msg);

    public boolean sendVerificationEmail(Account account,String verify);

    boolean sendmailResetpassword(Account account, String s);

    void sendOrderMail(Account account, Bill order, List<Bill_detail> billDetails);
}
