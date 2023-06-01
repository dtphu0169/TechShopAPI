package com.tech.TechShopAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
@CrossOrigin(origins = "*")
public class DemoControlller {
    @GetMapping("/headers")
    public ResponseEntity<?> myEndpoint(@RequestHeader Map<String, String> headers) {
        for (Map.Entry<String ,String> en : headers.entrySet()){
            System.out.println(en.getKey() +" - "+en.getValue());
        }
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/google")
    public Map<String,Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    @GetMapping("/oauth2/callback/google")
    public ResponseEntity<String> handleGoogleCallback(@RequestParam("code") String code) {
        // Exchange the authorization code for an access token
        // Use the access token to retrieve the user's profile information from Google
        // Generate a JWT token and return it to the frontend
        return ResponseEntity.ok("Success");
    }
}
