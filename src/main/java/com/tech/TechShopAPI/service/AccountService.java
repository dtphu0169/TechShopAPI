package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface AccountService {
    List<Account> getAllAccount();

    String getEmailbyId(int id);

    boolean isEmailhasAccount(String email);

    AccountDto getInfo(Principal principal);

    void changepassword(Principal principal, String password);
}
