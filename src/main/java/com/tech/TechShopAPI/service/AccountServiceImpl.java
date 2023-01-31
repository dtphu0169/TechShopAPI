package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public String getEmailbyId(int id) {
        return accountRepository.getEmailbyId(id);
    }



}
