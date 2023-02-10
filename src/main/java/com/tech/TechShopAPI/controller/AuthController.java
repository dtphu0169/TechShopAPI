package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.model.AccountSecurity;
import com.tech.TechShopAPI.payload.request.LoginRequest;
import com.tech.TechShopAPI.payload.request.SignupRequest;
import com.tech.TechShopAPI.payload.response.JwtResponse;
import com.tech.TechShopAPI.repository.AccountRepository;
import com.tech.TechShopAPI.service.AccountService;
import com.tech.TechShopAPI.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    TokenService tokenService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenService.generateToken(authentication);
            AccountSecurity accountSecurity = (AccountSecurity) authentication.getPrincipal();
            JwtResponse response = new JwtResponse();
            response.setToken(jwt);
            response.setEmail(accountSecurity.getAccount().getEmail());
            response.setName(accountSecurity.getAccount().getUserName());
            response.setPhone(accountSecurity.getAccount().getPhone());
            response.setId(accountSecurity.getAccount().getId());
            response.setRole(accountSecurity.getAccount().getRole());
            return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);

//            return new ResponseEntity<String>("response",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> Register(@RequestBody SignupRequest signupRequest){
        if (accountService.isEmailhasAccount(signupRequest.getEmail())){
            return new ResponseEntity<String>("Email registed",HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();
        account.setUserName(signupRequest.getUserName());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(encoder.encode(signupRequest.getPassword()));
        account.setPhone(signupRequest.getPhone());
        account.setRole("ROLE_USER");
        account.setActive(false);

        java.sql.Date now = new Date(System.currentTimeMillis());
        account.setRegisterDate(now);

        accountRepository.save(account);
        return new ResponseEntity<Account>(account,HttpStatus.OK);

    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        log.debug("Token requested for user: '{}'",authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granged {}",token);
        return token;
    }
    @PostMapping("/EmailhasAccount")
    public ResponseEntity<?> checkMail(@RequestBody SignupRequest signupRequest){
        boolean hasAccount = accountService.isEmailhasAccount(signupRequest.getEmail());
        return new ResponseEntity<Boolean>(hasAccount,HttpStatus.OK);
    }
}
