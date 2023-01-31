package com.tech.TechShopAPI.controller;

import com.tech.TechShopAPI.model.Account;
import com.tech.TechShopAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    // test api role
//    @PreAuthorize("hasRole('USER')") // các đường dẫn chưa đề cập trong sẽ được authenticated nên không cần role user
    @GetMapping("/user")
    public String user(Principal principal) {
        return "Hello, "+ principal.getName();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }


    @GetMapping("/users")
    public ResponseEntity<List<Account>> getAll(){
        try{
            List<Account> list = accountService.getAllAccount();
            if (list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
            }
            return new ResponseEntity<List<Account>>(list, HttpStatus.OK) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<String> getemail(@PathVariable int id){
        try{
            String email = accountService.getEmailbyId(id);
            if (email == null || email.equals("")){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
            }
            return new ResponseEntity<String>(email, HttpStatus.OK) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


//    List<Account> accounts = new ArrayList<>();
//
//    @GetMapping("/getAll")
//    List<Account> getAccounts(){
//        List<Account> list = new ArrayList<>();
//        Account ac1 = new Account();
//        ac1.setId(1);
//        ac1.setUserName("ac1");
//        ac1.setEmail("ac223@gmail.com");
//        ac1.setPassword("1234");
//        ac1.setRole(true);
//        list.add(ac1);
//
//        Account ac2 = new Account();
//        ac2.setId(2);
//        ac2.setUserName("ac2");
//        ac2.setEmail("acxx@gmail.com");
//        ac2.setPassword("1235");
//        ac2.setRole(false);
//        list.add(ac2);
//
//        list.addAll(accounts);
//        return list;
//
//    }
//
//    @PostMapping("/insert")
//    Account insertAccount(@RequestBody Account account){
//        accounts.add(account);
//        return account;
//    }


}
