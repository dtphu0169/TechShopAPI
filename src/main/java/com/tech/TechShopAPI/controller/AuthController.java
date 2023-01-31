package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    AuthController(TokenService tokenService){
        this.tokenService = tokenService;
    }


    @GetMapping("/login")
    public boolean login(){
        return true;
    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        log.debug("Token requested for user: '{}'",authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granged {}",token);
        return token;
    }
}
