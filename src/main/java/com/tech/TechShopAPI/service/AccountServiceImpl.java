package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public String getEmailbyId(int id) {
        return accountRepository.getEmailbyId(id);
    }

    @Override
    public boolean isEmailhasAccount(String email) {
        Optional<Account> account = accountRepository.getbyEmail(email);
        if (account.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public AccountDto getInfo(Principal principal) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        AccountDto accountDto = Dtomapper.mapAccount(account);
        return accountDto;
    }

    @Override
    public void changepassword(Principal principal, String password) {
        Account account = accountRepository.getbyEmail(principal.getName()).get();
        account.setPassword(encoder.encode(password));
        accountRepository.save(account);
    }


}
