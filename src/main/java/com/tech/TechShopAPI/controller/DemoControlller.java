package com.tech.TechShopAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
