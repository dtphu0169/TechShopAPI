package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> getAllAccount();

    String getEmailbyId(int id);
}
