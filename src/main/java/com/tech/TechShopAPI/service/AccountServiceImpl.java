package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.payload.request.SignupRequest;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SendmailService sendmailService;
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

    @Override
    public Account register(SignupRequest signupRequest) {
        Account account = new Account();
        account.setUserName(signupRequest.getUserName());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(encoder.encode(signupRequest.getPassword()));
        account.setPhone(signupRequest.getPhone());
        account.setRole("ROLE_USER");
        account.setActive(false);

        java.sql.Date now = new Date(System.currentTimeMillis());
        account.setRegisterDate(now);

        String token = String.valueOf(UUID.randomUUID());
        account.setToken(token);
        sendmailService.sendVerificationEmail(account,"http://localhost:8080/api/auth/verify/"+token);

        return accountRepository.save(account);
    }

    @Override
    public boolean checkToken(String token) {
        Optional<Account> accountOptional = accountRepository.findBytoken(token);
        if (accountOptional.isEmpty()){
            return false;
        }
        Account account = accountOptional.get();
        account.setActive(true);
        account.setToken(null);
        accountRepository.save(account);
        return true;
    }

    @Override
    public void forgetpassword(String email) {
        Optional<Account> accountOptional = accountRepository.getbyEmail(email);
        if (accountOptional.isEmpty()){
            return;
        }
        Account account = accountOptional.get();
        account.setActive(true);
        String token = String.valueOf(UUID.randomUUID());
        account.setToken(token);

        sendmailService.sendmailResetpassword(account,"http://localhost:8080/api/auth/resetpassword/"+token);
        accountRepository.save(account);
    }

    @Override
    public String verifyResetPassword(String token) {
        String pass = token.substring(1,6);
        Optional<Account> accountOptional = accountRepository.findBytoken(token);
        if (accountOptional.isEmpty()){
            return null;
        }
        Account account = accountOptional.get();
        account.setActive(true);
        account.setToken(null);
        account.setPassword(encoder.encode(pass));
        accountRepository.save(account);

        return pass;
    }


}
