package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.model.AccountSecurity;
import com.tech.TechShopAPI.payload.request.LoginRequest;
import com.tech.TechShopAPI.payload.response.JwtResponse;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;

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

    @PostMapping("/token")
    public String token(Authentication authentication){
        log.debug("Token requested for user: '{}'",authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granged {}",token);
        return token;
    }
}
