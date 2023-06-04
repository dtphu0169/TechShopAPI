package com.tech.TechShopAPI.service;

import com.tech.TechShopAPI.dto.AccountDto;
import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.payload.request.SignupRequest;
import com.tech.TechShopAPI.payload.response.VerifyTokenResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface AccountService {
    List<AccountDto> getAllAccount();

    String getEmailbyId(int id);

    boolean isEmailhasAccount(String email);

    AccountDto getInfo(Principal principal);

    void changepassword(Principal principal, String password);

    AccountDto register(SignupRequest signupRequest);

    VerifyTokenResponse checkToken(String token);

    void forgetpassword(String email);

    String verifyResetPassword(String token);

    Account getByEmail(String email);
}
