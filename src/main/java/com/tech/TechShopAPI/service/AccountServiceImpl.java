package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.dto.Dtomapper;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.payload.request.SignupRequest;
import com.tech.TechShopAPI.payload.response.VerifyTokenResponse;
import com.tech.TechShopAPI.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.*;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SendmailService sendmailService;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<AccountDto> getAllAccount() {
        List<AccountDto> list = new ArrayList<>();
        for(Account account : accountRepository.findAll()){
            AccountDto dto = Dtomapper.mapAccount(account);
            list.add(dto);
        }
        return list;
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
    public AccountDto register(SignupRequest signupRequest) {
        Account account = new Account();
        account.setUserName(signupRequest.getUserName());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(encoder.encode(signupRequest.getPassword()));
        account.setPhone(signupRequest.getPhone());
        account.setRole("ROLE_USER");
        account.setActive(false);

        Date now = new Date(System.currentTimeMillis());
        account.setRegisterDate(now);

        String token = String.valueOf(UUID.randomUUID());
        account.setToken(token);
        sendmailService.sendVerificationEmail(account,"http://localhost:3000/verify/"+token);

        AccountDto dto = Dtomapper.mapAccount(accountRepository.save(account));
        return dto;
    }

    @Override
    public VerifyTokenResponse checkToken(String token) {
        Optional<Account> accountOptional = accountRepository.findBytoken(token);
        VerifyTokenResponse response = new VerifyTokenResponse();
        if (accountOptional.isEmpty()){
            response.setStatus("Verification failed!");
            response.setNote("Token đã được sử dung. "
                    +"Vui lòng đăng nhập để tiếp tục!");
            return response;
        }
        Account account = accountOptional.get();
        account.setActive(true);
        account.setToken(null);
        accountRepository.save(account);
        response.setStatus("Verification successful!");
        response.setNote("Xác minh tài khoản thành công! "+
                "Vui lòng đăng nhập để tiếp tục!");
        return response;
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

    @Override
    public Account getByEmail(String email) {
        Optional<Account> accountOptional = accountRepository.getbyEmail(email);
        if (accountOptional.isEmpty()){
            return null;
        }
        return accountOptional.get();
    }


}
